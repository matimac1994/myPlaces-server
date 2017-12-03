package com.maciejak.myplaces_server.api.mappers;

import com.maciejak.myplaces_server.api.dto.response.PlacePhotoResponse;
import com.maciejak.myplaces_server.entity.PlacePhoto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlacePhotoMapper {

    PlacePhotoMapper INSTANCE = Mappers.getMapper(PlacePhotoMapper.class);

    PlacePhotoResponse placePhotoToPlaceResponse(PlacePhoto placePhoto);
}
