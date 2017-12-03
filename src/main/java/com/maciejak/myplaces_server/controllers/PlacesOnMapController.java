package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.response.PlaceMapResponse;
import com.maciejak.myplaces_server.services.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MyPlacesServerApplication.BASE_URL + "/map")
public class PlacesOnMapController {

    private PlaceService placeService;

    public PlacesOnMapController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PlaceMapResponse>> getActivePlacesOnMap(){
        return ResponseEntity.ok(placeService.getAllMapPlaces());
    }
}
