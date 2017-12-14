package com.maciejak.myplaces_server.services;

import com.maciejak.myplaces_server.api.dto.response.TopPlaceResponse;
import com.maciejak.myplaces_server.api.dto.response.TopPlaceResponseList;
import com.maciejak.myplaces_server.entity.TopPlace;

import java.util.List;

public interface TopPlacesService {

    List<TopPlaceResponseList> getTopPlaces();

    TopPlaceResponse getTopPlaceById(Long topPlaceId);

    void save(TopPlace topPlace);

    void save(List<TopPlace> topPlaces);

    void clear();
}
