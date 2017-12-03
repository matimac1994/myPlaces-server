package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.request.EditPlaceRequest;
import com.maciejak.myplaces_server.api.dto.response.PlaceResponse;
import com.maciejak.myplaces_server.entity.Place;
import com.maciejak.myplaces_server.services.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(MyPlacesServerApplication.BASE_URL + "/edit")
public class EditPlaceController {

    private PlaceService placeService;

    public EditPlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @PostMapping
    public ResponseEntity<Void> editPlace(@RequestBody EditPlaceRequest editPlaceRequest){
        placeService.editPlace(editPlaceRequest);
        return ResponseEntity.ok().build();
    }
}
