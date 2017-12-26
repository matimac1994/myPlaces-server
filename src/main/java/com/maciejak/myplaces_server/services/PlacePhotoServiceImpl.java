package com.maciejak.myplaces_server.services;

import com.maciejak.myplaces_server.api.dto.request.IdsRequest;
import com.maciejak.myplaces_server.api.dto.response.PlacePhotoResponse;
import com.maciejak.myplaces_server.api.mappers.PlacePhotoMapper;
import com.maciejak.myplaces_server.entity.Place;
import com.maciejak.myplaces_server.entity.PlacePhoto;
import com.maciejak.myplaces_server.exception.place.PlacePhotoNotFoundException;
import com.maciejak.myplaces_server.repositories.PlacePhotoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlacePhotoServiceImpl implements PlacePhotoService {

    private PlacePhotoRepository placePhotoRepository;
    private PlacePhotoMapper placePhotoMapper;
    private HttpServletRequest request;

    public PlacePhotoServiceImpl(PlacePhotoRepository placePhotoRepository, PlacePhotoMapper placePhotoMapper, HttpServletRequest request) {
        this.placePhotoRepository = placePhotoRepository;
        this.placePhotoMapper = placePhotoMapper;
        this.request = request;
    }

    @Override
    public List<PlacePhotoResponse> getPlacePhotos() {
        return placePhotoRepository.findAll()
                .stream()
                .map(placePhotoMapper::placePhotoToPlaceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PlacePhotoResponse getPlacePhotoById(Long photoId) {
        PlacePhoto placePhoto = placePhotoRepository.findOne(photoId);
        if (placePhoto == null){
            throw new PlacePhotoNotFoundException();
        }
        return placePhotoMapper.placePhotoToPlaceResponse(placePhoto);
    }

    @Override
    public List<PlacePhotoResponse> getPlacePhotosByIds(IdsRequest idsRequest) {
        List<PlacePhotoResponse> placePhotos = new ArrayList<>();
        for (Long id : idsRequest.getIds()){
            placePhotos.add(getPlacePhotoById(id));
        }
        return placePhotos;
    }

    @Override
    public void deletePlacePhotoById(Long photoId) {
        PlacePhoto placePhoto = placePhotoRepository.findOne(photoId);

        if (placePhoto == null){
            throw new PlacePhotoNotFoundException();
        }

        placePhotoRepository.delete(placePhoto);
    }

    @Override
    public void deletePlacePhotosByIds(IdsRequest idsRequest) {
        for (Long id : idsRequest.getIds()){
            deletePlacePhotoById(id);
        }
    }

    @Override
    public PlacePhotoResponse savePhoto(MultipartFile photo) {
        String filePath =request.getServletContext().getRealPath("/");
        return new PlacePhotoResponse();
    }

    @Override
    public List<PlacePhoto> savePhotos(Place place, MultipartFile[] photos) {
        return null;
    }
}
