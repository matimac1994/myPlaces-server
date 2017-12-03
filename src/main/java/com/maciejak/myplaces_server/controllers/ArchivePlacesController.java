package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.request.PlaceIdsRequest;
import com.maciejak.myplaces_server.api.dto.response.PlaceListResponse;
import com.maciejak.myplaces_server.services.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MyPlacesServerApplication.BASE_URL + "/archive")
public class ArchivePlacesController {

    private PlaceService placeService;

    public ArchivePlacesController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<PlaceListResponse>> getAllArchivePlaces(){
        return ResponseEntity.ok(placeService.getAllArchivedPlaceList());
    }

    @PatchMapping
    public ResponseEntity<Void> restorePlaces(@RequestBody PlaceIdsRequest placeIdsRequest){
        placeService.restorePlaces(placeIdsRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletePlaces(@RequestBody PlaceIdsRequest placeIdsRequest){
        placeService.deletePlaces(placeIdsRequest);
        return ResponseEntity.ok().build();
    }
}
