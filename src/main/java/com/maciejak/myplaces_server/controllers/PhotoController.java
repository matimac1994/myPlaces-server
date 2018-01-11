package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.services.StorageService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping(PhotoController.PHOTO_END_POINT)
@RestController
public class PhotoController {

    public static final String PHOTO_END_POINT = MyPlacesServerApplication.BASE_URL + "/photo";

    private StorageService storageService;

    public PhotoController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping(value = "/{userId}/{fileName:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getPhoto(@PathVariable("userId") Long userId,
            @PathVariable String fileName){
        return storageService.getPhoto(userId, fileName);
    }

}
