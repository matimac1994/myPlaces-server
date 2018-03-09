package com.maciejak.myplaces_server.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapPhotoUtilTest {

    private static final int HEIGHT = 500;
    private static final int WIDTH = 1000;
    private static final Double LATITUDE = 53.5345;
    private static final Double LONGITUDE = 23.32452;
    private static final int ZOOM = 10;

    private static final String EXPECTED_URL = "https://maps.googleapis.com/maps/api/staticmap?center=" +
            LATITUDE + "," + LONGITUDE + "&" +
            "zoom=" + ZOOM + "&" +
            "size=" + WIDTH + "x" + HEIGHT + "&" +
            "markers=" + LATITUDE + "," + LONGITUDE;



    private MapPhotoUtil mapPhotoUtil;

    @Before
    public void setUp() throws Exception {
        mapPhotoUtil = new MapPhotoUtil(LATITUDE, LONGITUDE);
        mapPhotoUtil.setHeight(HEIGHT);
        mapPhotoUtil.setWidth(WIDTH);
        mapPhotoUtil.setZoom(ZOOM);
    }

    @Test
    public void createUrlForMapImage() throws Exception {
        assertEquals(EXPECTED_URL, mapPhotoUtil.createUrlForMapImage());
    }

}