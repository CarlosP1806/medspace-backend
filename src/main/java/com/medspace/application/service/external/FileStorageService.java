package com.medspace.application.service.external;

import java.io.InputStream;

public interface FileStorageService {
    public String saveFile(InputStream inputStream);
}
