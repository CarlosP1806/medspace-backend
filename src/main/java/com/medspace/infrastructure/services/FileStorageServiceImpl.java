package com.medspace.infrastructure.services;

import com.medspace.application.service.external.FileStorageService;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.InputStream;

@ApplicationScoped
public class FileStorageServiceImpl implements FileStorageService {
    public String saveFile(InputStream inputStream) {
        // TODO: use buckets
        return "http://sample-endpoint.com";
    }
}
