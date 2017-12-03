package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.response.PlaceResponse;
import com.maciejak.myplaces_server.services.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;

@RestController
@RequestMapping(MyPlacesServerApplication.BASE_URL)
public class ShowPlaceController {

    private PlaceService placeService;

    public ShowPlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/showplace/{placeId}")
    @ResponseBody
    public ResponseEntity<PlaceResponse> getPlaceById(@PathVariable(value = "placeId") Long placeId){
        return ResponseEntity.ok(placeService.getPlaceById(placeId));
    }

    @DeleteMapping("/delete/{placeId}")
    public ResponseEntity<Void> deletePlaceById(@PathVariable(value = "placeId") Long placeId){
        placeService.deletePlace(placeId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/restore/{placeId}")
    public ResponseEntity<Void> restorePlaceById(@PathVariable(value = "placeId") Long placeId){
        placeService.restorePlace(placeId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/archive/{placeId}")
    public ResponseEntity<Void> archivePlaceById(@PathVariable(value = "placeId") Long placeId){
        placeService.archivePlace(placeId);
        return ResponseEntity.ok().build();
    }


}
