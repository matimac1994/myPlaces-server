package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.api.dto.request.AddPlaceRequest;
import com.maciejak.myplaces_server.api.dto.request.EditPlaceRequest;
import com.maciejak.myplaces_server.api.dto.request.IdsRequest;
import com.maciejak.myplaces_server.api.dto.response.AddPlaceResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceListResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceMapResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceResponse;
import com.maciejak.myplaces_server.services.PlaceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/myplaces")
public class PlacesController {

    private PlaceService placeService;

    public PlacesController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping()
    @ResponseBody
    public ResponseEntity<List<PlaceResponse>> getAllPlaces(){
        return ResponseEntity.ok(placeService.getAllPlaces());
    }

    @GetMapping("/active")
    @ResponseBody
    public ResponseEntity<List<PlaceListResponse>> getAllActivePlaces(){
        return ResponseEntity.ok(placeService.getAllActivePlacesList());
    }

    @GetMapping("/archived")
    @ResponseBody
    public ResponseEntity<List<PlaceListResponse>> getAllArchivePlaces(){
        return ResponseEntity.ok(placeService.getAllArchivedPlaceList());
    }

    @GetMapping("/map")
    @ResponseBody
    public ResponseEntity<List<PlaceMapResponse>> getActivePlacesOnMap(){
        return ResponseEntity.ok(placeService.getAllMapPlaces());
    }

    @PatchMapping("/restore")
    public ResponseEntity<Void> restorePlaces(@RequestBody IdsRequest idsRequest){
        placeService.restorePlaces(idsRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deletePlaces(@RequestBody IdsRequest idsRequest){
        placeService.deletePlaces(idsRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<AddPlaceResponse> addPlace(@RequestBody AddPlaceRequest addPlaceRequest){
        return ResponseEntity.ok(placeService.addPlace(addPlaceRequest));
    }

    @PostMapping("/edit")
    public ResponseEntity<Void> editPlace(@RequestBody EditPlaceRequest editPlaceRequest){
        placeService.editPlace(editPlaceRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{placeId}")
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
