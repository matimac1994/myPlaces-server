package com.maciejak.myplaces_server.services;

import org.springframework.web.multipart.MultipartFile;


public interface StorageService {

    String store(MultipartFile file);

    byte[] getPhoto(Long userId, String fileName);

    String createFileServerUrl(Long userId, String filename);

    boolean deletePhotoByPath(String photoPath);
}
