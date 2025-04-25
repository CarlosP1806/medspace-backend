
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.IOException;
import java.io.InputStream;

@ApplicationScoped
public class FirebaseInitializer {

    @PostConstruct
    public void init() {
        try {
            InputStream serviceAccount = getClass().getResourceAsStream("/firebase/serviceAccountKey.json");

            if (serviceAccount == null) {
                throw new IllegalStateException("No se encontró el archivo de credenciales Firebase.");
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al inicializar Firebase", e);
        }
    }
}
