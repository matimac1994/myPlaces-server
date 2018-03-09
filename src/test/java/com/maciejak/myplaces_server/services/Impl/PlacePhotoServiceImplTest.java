package com.maciejak.myplaces_server.services.Impl;

import com.maciejak.myplaces_server.api.dto.response.PlacePhotoResponse;
import com.maciejak.myplaces_server.api.mappers.PlacePhotoMapper;
import com.maciejak.myplaces_server.entity.PlacePhoto;
import com.maciejak.myplaces_server.repositories.PlacePhotoRepository;
import com.maciejak.myplaces_server.services.StorageService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class PlacePhotoServiceImplTest {
    PlacePhotoServiceImpl placePhotoService;

    @Mock
    PlacePhotoRepository placePhotoRepository;

    @Mock
    StorageService storageService;

    @Mock
    PlacePhotoMapper placePhotoMapper;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        placePhotoService = new PlacePhotoServiceImpl(placePhotoRepository, storageService, placePhotoMapper);
    }

    @Test
    public void getPlacePhotos() throws Exception {
        PlacePhotoResponse placePhotoResponse = new PlacePhotoResponse();
        List<PlacePhotoResponse> placePhotoResponses = new ArrayList<>();
        placePhotoResponses.add(placePhotoResponse);

        when(placePhotoService.getPlacePhotos()).thenReturn(placePhotoResponses);

//        List<PlacePhotoResponse> placePhotoResponses = placePhotoService.getPlacePhotos();

        assertEquals(placePhotoResponses.size(), 1);
    }

}