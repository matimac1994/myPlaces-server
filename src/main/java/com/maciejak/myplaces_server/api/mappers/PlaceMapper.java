package com.maciejak.myplaces_server.api.mappers;

import com.maciejak.myplaces_server.api.dto.request.AddPlaceRequest;
import com.maciejak.myplaces_server.api.dto.response.PlaceMapResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceListResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceResponse;
import com.maciejak.myplaces_server.entity.Place;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlaceMapper {

    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);

    PlaceResponse placeToPlaceResponse(Place place);

    Place addPlaceRequestToPlace(AddPlaceRequest addPlaceRequest);

    PlaceMapResponse placeToMapPlaceResponse(Place place);

    PlaceListResponse placeToPlaceListResponse(Place place);

    Place placeResponseToPlace(PlaceResponse placeResponse);
}
