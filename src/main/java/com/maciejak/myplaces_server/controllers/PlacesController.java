package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.api.dto.response.PlaceListResponse;
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

//    PlaceService placeService;
//
//    public PlaceController(PlaceService placeService) {
//        this.placeService = placeService;
//    }
//
//    @GetMapping
//    @ResponseStatus(HttpStatus.OK)
//    public PlaceListDTO getAllPlaces() {
//        return new PlaceListDTO(placeService.getAllPlaces());
//    }
//
//    @GetMapping("/{placeId)")
//    @ResponseStatus(HttpStatus.OK)
//    public PlaceDTO getPlaceById(@PathVariable Long placeId){
//        return placeService.getPlaceById(placeId);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void addPlace(@RequestBody PlaceDTO placeDTO){
//        placeService.addPlace(placeDTO);
//    }
//
//    @PatchMapping
//    @ResponseStatus(HttpStatus.ACCEPTED)
//    public void editPlace(@RequestBody PlaceDTO placeDTO){
//        placeService.editPlace(placeDTO);
//    }
//
//    @DeleteMapping("/{placeId}")
//    @ResponseStatus(HttpStatus.OK)
//    public void deletePlace(@PathVariable Long placeId){
//        placeService.deletePlace(placeId);
//    }
}
