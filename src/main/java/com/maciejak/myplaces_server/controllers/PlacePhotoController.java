package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.request.IdsRequest;
import com.maciejak.myplaces_server.api.dto.response.PlacePhotoResponse;
import com.maciejak.myplaces_server.services.PlacePhotoService;
import com.maciejak.myplaces_server.services.StorageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(PlacePhotoController.PLACE_PHOTO_END_POINT)
@PreAuthorize("isAuthenticated()")
public class PlacePhotoController {

    public static final String PLACE_PHOTO_END_POINT = MyPlacesServerApplication.BASE_URL + "/photos";
    private PlacePhotoService placePhotoService;

    public PlacePhotoController(PlacePhotoService placePhotoService) {
        this.placePhotoService = placePhotoService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PlacePhotoResponse>> getPhotos(){
        return ResponseEntity.ok(placePhotoService.getPlacePhotos());
    }

    @GetMapping("/byids")
    @ResponseBody
    public ResponseEntity<List<PlacePhotoResponse>> getPhotosByIds(@RequestBody IdsRequest idsRequest){
        return ResponseEntity.ok(placePhotoService.getPlacePhotosByIds(idsRequest));
    }

    @GetMapping("/byid/{photoId}")
    @ResponseBody
    public ResponseEntity<PlacePhotoResponse> getPhotoById(@PathVariable Long photoId){
        return ResponseEntity.ok(placePhotoService.getPlacePhotoById(photoId));
    }

    @PostMapping("/upload/{placeId}")
    public ResponseEntity<Void> handlePhotoUpload(
            @PathVariable("placeId") Long placeId,
            @RequestParam("photo") MultipartFile photo){
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/upload", consumes = "multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity<PlacePhotoResponse> handlePhotoUpload(
            @RequestParam("photo") MultipartFile photo){
        return ResponseEntity.ok(placePhotoService.savePhoto(photo));
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deletePhotosByIds(@RequestBody IdsRequest idsRequest){
        placePhotoService.deletePlacePhotosByIds(idsRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{photoId}")
    public ResponseEntity<Void> deletePhotoById(@PathVariable Long photoId){
        placePhotoService.deletePlacePhotoById(photoId);
        return ResponseEntity.ok().build();
    }

}
