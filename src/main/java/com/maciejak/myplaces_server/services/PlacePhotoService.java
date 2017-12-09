package com.maciejak.myplaces_server.services;

import com.maciejak.myplaces_server.api.dto.request.IdsRequest;
import com.maciejak.myplaces_server.api.dto.response.PlacePhotoResponse;
import com.maciejak.myplaces_server.repositories.PlacePhotoRepository;

import java.util.List;

public interface PlacePhotoService {

    List<PlacePhotoResponse> getPlacePhotos();
    PlacePhotoResponse getPlacePhotoById(Long photoId);
    List<PlacePhotoResponse> getPlacePhotosByIds(IdsRequest idsRequest);
    void deletePlacePhotoById(Long photoId);
    void deletePlacePhotosByIds(IdsRequest idsRequest);
}
