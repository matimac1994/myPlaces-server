package com.maciejak.myplaces_server.services;

import com.maciejak.myplaces_server.api.dto.request.AddPlaceRequest;
import com.maciejak.myplaces_server.api.dto.request.EditPlaceRequest;
import com.maciejak.myplaces_server.api.dto.request.PlaceIdsRequest;
import com.maciejak.myplaces_server.api.dto.response.AddPlaceResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceMapResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceListResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceResponse;

import java.util.List;

public interface PlaceService {

    List<PlaceResponse> getAllPlaces();

    List<PlaceResponse> getAllActivePlaces();

    List<PlaceResponse> getAllArchivedPlaces();

    List<PlaceMapResponse> getAllMapPlaces();

    List<PlaceListResponse> getAllActivePlacesList();

    List<PlaceListResponse> getAllArchivedPlaceList();

    PlaceResponse getPlaceById(Long placeId);

    AddPlaceResponse addPlace(AddPlaceRequest addPlaceRequest);

    void editPlace(EditPlaceRequest editPlaceRequest);

    void deletePlace(Long placeId);

    void restorePlace(Long placeId);

    void restorePlaces(PlaceIdsRequest placeIdsRequest);

    void deletePlaces(PlaceIdsRequest placeIdsRequest);

    void archivePlace(Long placeId);
}
