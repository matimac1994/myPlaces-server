package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.response.TopPlaceResponse;
import com.maciejak.myplaces_server.api.dto.response.TopPlaceResponseList;
import com.maciejak.myplaces_server.services.TopPlacesService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(MyPlacesServerApplication.BASE_URL + "/top")
@PreAuthorize("isAuthenticated()")
public class TopPlacesController {

    private TopPlacesService topPlacesService;

    public TopPlacesController(TopPlacesService topPlacesService) {
        this.topPlacesService = topPlacesService;
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<TopPlaceResponseList>> getTopPlaces(){
        return ResponseEntity.ok(topPlacesService.getTopPlaces());
    }

    @GetMapping("/{topPlaceId}")
    @ResponseBody
    public ResponseEntity<TopPlaceResponse> getTopPlaces(@PathVariable(value = "topPlaceId") Long topPlaceId){
        return ResponseEntity.ok(topPlacesService.getTopPlaceById(topPlaceId));
    }
}
