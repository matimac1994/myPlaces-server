package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.request.IdsRequest;
import com.maciejak.myplaces_server.api.dto.response.PlacePhotoResponse;
import com.maciejak.myplaces_server.services.PlacePhotoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MyPlacesServerApplication.BASE_URL + "/photos")
public class PlacePhotoController {

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

    @GetMapping("/{photoId}")
    @ResponseBody
    public ResponseEntity<PlacePhotoResponse> getPhotoById(@PathVariable Long photoId){
        return ResponseEntity.ok(placePhotoService.getPlacePhotoById(photoId));
    }


    @PostMapping("/delete")
    public ResponseEntity<Void> deletePhotosByIds(@RequestBody IdsRequest idsRequest){
        placePhotoService.deletePlacePhotosByIds(idsRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{photoId}")
    public ResponseEntity<Void> deletePhotoById(@PathVariable Long photoId){
        placePhotoService.getPlacePhotoById(photoId);
        return ResponseEntity.ok().build();
    }

}
