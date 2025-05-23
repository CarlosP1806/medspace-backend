package com.medspace.infrastructure.client;

import com.medspace.domain.model.ExternalClinic;
import com.medspace.domain.repository.ExternalClinicRepository;
import com.medspace.infrastructure.dto.externalClinic.ExternalClinicResponseDTO;
import com.medspace.infrastructure.mapper.ExternalClinicResponseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;
import java.net.URLEncoder;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.time.Instant;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ExternalClinicApiClient {
    private static final Logger LOGGER = Logger.getLogger(ExternalClinicApiClient.class.getName());
    private static final int CONNECT_TIMEOUT = 30000; // 30 seconds
    private static final int READ_TIMEOUT = 210000; // 210 seconds
    private static final int BUFFER_SIZE = 22000000; // 22 MB buffer
    private static final int MAX_RETRIES = 3;
    private static final int INITIAL_DELAY_MS = 2000; // 2 seconds
    private static final int MAX_DELAY_MS = 10000; // 10 seconds
    private static final int BATCH_SIZE = 1000; // Fetch 1000 records at a time
    private static final int EXACT_RECORDS = 100;

    private final String apiUrl;
    private final String authToken;
    private final ObjectMapper objectMapper;
    private final JsonFactory jsonFactory;
    private final ExternalClinicRepository repository;

    @Inject
    public ExternalClinicApiClient(ExternalClinicRepository repository) {
        this.apiUrl =
                "https://www.inegi.org.mx/app/api/denue/v1/consulta/BuscarEntidad/Consultorios de medicina especializada del sector privado/00/1/100000/7a7f67dc-2ce2-4160-b2b6-6ba8f7c933e1";
        this.authToken = this.apiUrl.substring(this.apiUrl.lastIndexOf("/") + 1);
        this.objectMapper = new ObjectMapper();
        this.jsonFactory = new JsonFactory();
        this.repository = repository;
    }

    private String buildUrl(int page, int pageSize) {
        try {
            String cleanUrl = apiUrl.replaceAll("[\\u200B-\\u200D\\uFEFF]", "");
            String[] parts = cleanUrl.split("\\?");
            String baseUrl = parts[0];
            String query = parts.length > 1 ? parts[1] : "";

            String[] pathParts = baseUrl.split("/");
            StringBuilder encodedPath = new StringBuilder();
            for (int i = 0; i < pathParts.length; i++) {
                if (i > 0)
                    encodedPath.append("/");
                if (i >= 5) {
                    String encodedPart =
                            URLEncoder.encode(pathParts[i].trim(), StandardCharsets.UTF_8.name())
                                    .replace("+", "%20");
                    encodedPath.append(encodedPart);
                } else {
                    encodedPath.append(pathParts[i]);
                }
            }

            String finalUrl = encodedPath.toString();
            if (!query.isEmpty()) {
                finalUrl += "?" + query;
            }

            if (!finalUrl.contains(authToken)) {
                finalUrl += "/" + authToken;
            }

            // Replace with pagination parameters
            finalUrl = finalUrl.replace("/1/100000/", "/" + page + "/" + pageSize + "/");

            LOGGER.info("Final URL: " + finalUrl);
            return finalUrl;
        } catch (Exception e) {
            LOGGER.severe("Error building URL: " + e.getMessage());
            return apiUrl;
        }
    }

    private HttpURLConnection createConnection(URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setConnectTimeout(CONNECT_TIMEOUT);
        conn.setReadTimeout(READ_TIMEOUT);
        conn.setDoInput(true);
        conn.setInstanceFollowRedirects(true);

        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        conn.setRequestProperty("Connection", "keep-alive");
        conn.setRequestProperty("User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
        conn.setRequestProperty("Cache-Control", "no-cache");
        conn.setRequestProperty("Pragma", "no-cache");

        LOGGER.info("Setting up connection with headers:");
        conn.getRequestProperties().forEach((key, value) -> {
            if (key != null) {
                LOGGER.info(key + ": " + value);
            }
        });

        return conn;
    }

    private String readErrorResponse(InputStream errorStream) throws Exception {
        if (errorStream == null)
            return null;

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[BUFFER_SIZE];
        int length;
        while ((length = errorStream.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }

    @Transactional
    public List<ExternalClinic> fetchClinics() {
        List<ExternalClinic> allClinics = new ArrayList<>();
        int currentPage = 1;
        int totalPages = (int) Math.ceil((double) EXACT_RECORDS / BATCH_SIZE);

        while (currentPage <= totalPages) {
            int retryCount = 0;
            long delay = INITIAL_DELAY_MS;
            boolean success = false;

            while (retryCount < MAX_RETRIES && !success) {
                HttpURLConnection conn = null;
                InputStream inputStream = null;

                try {
                    if (retryCount > 0) {
                        LOGGER.info("Retry attempt " + retryCount + " after " + delay + "ms delay");
                        sleep(delay);
                    }

                    String finalUrl = buildUrl(currentPage, BATCH_SIZE);
                    LOGGER.info("Fetching clinics from URL: " + finalUrl);

                    URL url = new URL(finalUrl);
                    conn = createConnection(url);

                    int responseCode = conn.getResponseCode();
                    LOGGER.info("Received response code: " + responseCode);

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        inputStream = conn.getInputStream();
                        List<ExternalClinicResponseDTO> dtos = new ArrayList<>();

                        try (JsonParser parser = jsonFactory.createParser(inputStream)) {
                            if (parser.nextToken() != JsonToken.START_ARRAY) {
                                throw new RuntimeException("Expected array of clinics");
                            }

                            while (parser.nextToken() != JsonToken.END_ARRAY) {
                                ExternalClinicResponseDTO dto = objectMapper.readValue(parser,
                                        ExternalClinicResponseDTO.class);
                                dtos.add(dto);
                            }
                        }

                        List<ExternalClinic> batchClinics =
                                ExternalClinicResponseMapper.toDomainList(dtos);
                        LOGGER.info("Successfully fetched batch " + currentPage + " with "
                                + batchClinics.size() + " clinics");

                        // Set creation timestamp for all clinics in this batch
                        Instant now = Instant.now();
                        for (ExternalClinic clinic : batchClinics) {
                            clinic.setCreatedAt(now);
                        }

                        // Save this batch
                        repository.saveAll(batchClinics);
                        allClinics.addAll(batchClinics);
                        success = true;
                    } else if (responseCode == 429
                            || responseCode == HttpURLConnection.HTTP_UNAVAILABLE) {
                        LOGGER.warning("Rate limited or service unavailable, will retry...");
                        retryCount++;
                        delay = Math.min(delay * 2, MAX_DELAY_MS);
                        continue;
                    } else {
                        String errorMessage =
                                "Failed to fetch clinics. Response code: " + responseCode;
                        String errorResponse = readErrorResponse(conn.getErrorStream());
                        if (errorResponse != null) {
                            errorMessage += ". Error response: " + errorResponse;
                        }
                        throw new RuntimeException(errorMessage);
                    }
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error fetching clinics batch " + currentPage, e);
                    if (retryCount < MAX_RETRIES - 1) {
                        retryCount++;
                        delay = Math.min(delay * 2, MAX_DELAY_MS);
                        continue;
                    }
                    throw new RuntimeException("Error fetching clinics batch " + currentPage
                            + " after " + MAX_RETRIES + " attempts: " + e.getMessage(), e);
                } finally {
                    try {
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (conn != null) {
                            conn.disconnect();
                        }
                    } catch (Exception e) {
                        LOGGER.log(Level.WARNING, "Error closing resources", e);
                    }
                }
            }

            if (!success) {
                throw new RuntimeException("Failed to fetch clinics batch " + currentPage
                        + " after " + MAX_RETRIES + " attempts");
            }

            currentPage++;
        }

        return allClinics;
    }

    private void sleep(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
