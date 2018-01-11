package com.maciejak.myplaces_server.services.Impl;

import com.maciejak.myplaces_server.config.StorageProperties;
import com.maciejak.myplaces_server.controllers.PhotoController;
import com.maciejak.myplaces_server.controllers.PlacePhotoController;
import com.maciejak.myplaces_server.entity.User;
import com.maciejak.myplaces_server.exception.place.PlacePhotoNotFoundException;
import com.maciejak.myplaces_server.exception.storage.PhotoNotFoundException;
import com.maciejak.myplaces_server.exception.storage.StorageException;
import com.maciejak.myplaces_server.exception.storage.StorageFileNotFoundException;
import com.maciejak.myplaces_server.services.StorageService;
import com.maciejak.myplaces_server.utils.PrincipalProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.ZonedDateTime;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path rootLocation;
    private final String imageFormat;
    private final String serverHost;

    public StorageServiceImpl(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getRootFilesDirectory());
        this.imageFormat = properties.getImagesFormat();
        this.serverHost = properties.getServerHost();
    }

    @Override
    public String store(MultipartFile file) {
        User user = PrincipalProvider.getUserEntity();
        String fileName = createFileName(user.getId());
        String filePath = createFilePath(fileName, user.getId());

        try(FileOutputStream stream = new FileOutputStream(filePath)) {
            stream.write(file.getBytes());
        }catch (SecurityException e){
            e.printStackTrace();
            throw new StorageException("Store file security exception");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            throw new StorageFileNotFoundException("Store file not found");
        }catch (IOException e){
            e.printStackTrace();
            throw new StorageException("Store file IOException");
        }

        return filePath;
    }

    @Override
    public byte[] getPhoto(Long userId, String fileName) {
        String photoPath = createFilePath(fileName, userId);
        Path path = Paths.get(photoPath);

        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new PhotoNotFoundException(fileName + " not found");
        }
    }

    @Override
    public boolean deletePhotoByPath(String photoPath) {
        File file = new File(photoPath);
        return file.delete();
    }

    private String createFilePath(String fileName, Long userId) {
        File directory = new File(rootLocation.toString());
        openOrCreateDirectory(directory);
        String userDirectoryLocation = rootLocation.toString() + "\\" + userId;
        File userDirectory = new File(userDirectoryLocation);
        openOrCreateDirectory(userDirectory);

        return String.valueOf(userDirectoryLocation +
                "/" +
                fileName);
    }

    @Override
    public String createFileServerUrl(Long userId, String filename) {
        return String.valueOf("http://" +
                serverHost +
                PhotoController.PHOTO_END_POINT + "/" +
                userId + "/" +
                filename);
    }

    private void openOrCreateDirectory(File directory) {
        if (!directory.exists()){
            try{
                boolean result = directory.mkdirs();
                if(!result){
                    throw new StorageException("Failed to create directory");
                }
            }catch (SecurityException e){
                e.printStackTrace();
                throw new StorageException("Store Directory security exception");
            }

        }
    }

    private String createFileName(Long userId){
        return String.valueOf(userId) +
                "_" +
                ZonedDateTime.now().toInstant().toEpochMilli() +
                imageFormat;
    }
}
