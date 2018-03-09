package com.maciejak.myplaces_server.converters;

import com.maciejak.myplaces_server.api.dto.response.PlaceResponse;
import com.maciejak.myplaces_server.entity.Place;
import com.maciejak.myplaces_server.utils.MapPhotoUtil;
import org.springframework.core.convert.converter.Converter;

public class PlaceToPlaceResponseConverter implements Converter<Place, PlaceResponse> {
    @Override
    public PlaceResponse convert(Place place) {
        if (place == null){
            return new PlaceResponse();
        } else {
            PlaceResponse placeResponse = new PlaceResponse();
            placeResponse.setMapPhoto(new MapPhotoUtil(place.getLatitude(), place.getLongitude()).createUrlForMapImage());
            placeResponse.setDescription(place.getDescription());
            placeResponse.setNote(place.getNote());
            return placeResponse;
        }
    }
}
