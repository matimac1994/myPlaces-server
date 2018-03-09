package com.maciejak.myplaces_server.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlaceTest {

    Place place;

    @Before
    public void setUp(){
        place = new Place();
    }

    @Test
    public void getId() throws Exception {
        Long idValue = 1L;
        place.setId(idValue);

        assertEquals(idValue, place.getId());
    }

    @Test
    public void getTitle() throws Exception {
        String title = "Mateusz test";
        place.setTitle(title);
        assertEquals(title, place.getTitle());
        assertNotNull(place);
    }

    @Test
    public void getLatitude() throws Exception {
        Double latitude = 50.23423;
        Double longitude = 23.43535;
        place.setLatitude(latitude);
        place.setLongitude(longitude);

        assertEquals(latitude, place.getLatitude());
        assertEquals(longitude, place.getLongitude());
    }

}