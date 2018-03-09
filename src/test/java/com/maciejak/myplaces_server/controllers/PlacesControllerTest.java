package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.api.dto.response.PlaceMapResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceResponse;
import com.maciejak.myplaces_server.entity.Place;
import com.maciejak.myplaces_server.services.PlaceService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

public class PlacesControllerTest {

    private static final String BASE_URL = "/api/myplaces/";

    private static final int PLACE_ID = 1;
    private static final String NOTE = "sampleNote for place";
    private static final String DESCRIPTION = "Sample description with polish characters śćźżółęą";
    private static final String TITLE = "Title test";
    private static final Double LATITUDE = 54.43242;
    private static final Double LONGITUDE = 23.32423;

    private MockMvc mockMvc;

    @Mock
    private PlaceService placeService;

    private PlacesController placesController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        placesController = new PlacesController(placeService);

        mockMvc = MockMvcBuilders.standaloneSetup(placesController).build();
    }

    @Test
    public void getPlaceById() throws Exception {
        PlaceResponse place = new PlaceResponse();
        place.setId((long) PLACE_ID);
        place.setNote(NOTE);

        when(placeService.getPlaceById((long) PLACE_ID)).thenReturn(place);

        mockMvc.perform(get(BASE_URL + "{placeId}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is(PLACE_ID)))
                .andExpect(jsonPath("$.note", is(NOTE)));

        verify(placeService, times(1)).getPlaceById((long) PLACE_ID);
        verifyNoMoreInteractions(placeService);
    }

    @Test
    public void getActivePlacesOnMap() throws Exception {
        PlaceMapResponse placeMapResponse = new PlaceMapResponse();
        placeMapResponse.setId((long) PLACE_ID);
        placeMapResponse.setLatitude(LATITUDE);
        placeMapResponse.setLongitude(LONGITUDE);
        placeMapResponse.setDescription(DESCRIPTION);
        placeMapResponse.setTitle(TITLE);
        List<PlaceMapResponse> list = Collections.singletonList(placeMapResponse);

        when(placeService.getAllMapPlaces()).thenReturn(list);

        mockMvc.perform(get(BASE_URL + "map")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.[0].id", is(PLACE_ID)))
                .andExpect(jsonPath("$.[0].latitude", is(LATITUDE)))
                .andExpect(jsonPath("$.[0].longitude", is(LONGITUDE)))
                .andExpect(jsonPath("$.[0].description", is(DESCRIPTION)))
                .andExpect(jsonPath("$.[0].title", is(TITLE)));
    }

    @Test
    public void activePlacesOnMapShouldReturnEmptyList() throws Exception {
        List<PlaceMapResponse> emptyList = Collections.emptyList();

        when(placeService.getAllMapPlaces()).thenReturn(emptyList);

        mockMvc.perform(get(BASE_URL + "map")).andExpect(status().isOk());

        verify(placeService, times(1)).getAllMapPlaces();
    }
}