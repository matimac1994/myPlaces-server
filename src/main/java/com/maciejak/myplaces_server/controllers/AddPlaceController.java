package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.request.AddPlaceRequest;
import com.maciejak.myplaces_server.api.dto.response.AddPlaceResponse;
import com.maciejak.myplaces_server.services.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MyPlacesServerApplication.BASE_URL + "/addplace")
public class AddPlaceController {

    private PlaceService placeService;

    public AddPlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<AddPlaceResponse> addPlace(@RequestBody AddPlaceRequest addPlaceRequest){
        return ResponseEntity.ok(placeService.addPlace(addPlaceRequest));
    }
}
