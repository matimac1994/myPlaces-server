package com.maciejak.myplaces_server.converters;

import com.maciejak.myplaces_server.api.dto.response.PlaceResponse;
import com.maciejak.myplaces_server.entity.Place;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class PlaceToPlaceResponseConverterTest {

    private static final String NOTE = "COS TAM";

    private PlaceToPlaceResponseConverter placeToPlaceResponseConverter;

    private Place place;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        placeToPlaceResponseConverter = new PlaceToPlaceResponseConverter();
        place = new Place();
        place.setNote(NOTE);
    }

    @Test
    public void convert() throws Exception {
        PlaceResponse placeResponse = placeToPlaceResponseConverter.convert(place);

        assertEquals(NOTE, placeResponse.getNote());
        assertNotNull(place);
    }

}