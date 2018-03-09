package com.maciejak.myplaces_server.services.Impl;

import com.maciejak.myplaces_server.api.dto.request.IdsRequest;
import com.maciejak.myplaces_server.api.dto.response.PlacePhotoResponse;
import com.maciejak.myplaces_server.api.mappers.PlacePhotoMapper;
import com.maciejak.myplaces_server.entity.Place;
import com.maciejak.myplaces_server.entity.PlacePhoto;
import com.maciejak.myplaces_server.entity.User;
import com.maciejak.myplaces_server.exception.place.PlacePhotoNotFoundException;
import com.maciejak.myplaces_server.repositories.PlacePhotoRepository;
import com.maciejak.myplaces_server.services.PlacePhotoService;
import com.maciejak.myplaces_server.services.StorageService;
import com.maciejak.myplaces_server.utils.PrincipalProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlacePhotoServiceImpl implements PlacePhotoService {

    private PlacePhotoRepository placePhotoRepository;
    private StorageService storageService;
    private PlacePhotoMapper placePhotoMapper;

    public PlacePhotoServiceImpl(PlacePhotoRepository placePhotoRepository,
                                 StorageService storageService,
                                 PlacePhotoMapper placePhotoMapper) {
        this.placePhotoRepository = placePhotoRepository;
        this.storageService = storageService;
        this.placePhotoMapper = placePhotoMapper;
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
        return placePhotoMapper.placePhotoToPlaceResponse(findPlacePhotoByIdOrThrow(photoId));
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
        PlacePhoto placePhoto = findPlacePhotoByIdOrThrow(photoId);

        storageService.deletePhotoByPath(placePhoto.getPlacePhotoPath());
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
        User user = PrincipalProvider.getUserEntity();
        String filePath = storageService.store(photo);
        File file = new File(filePath);
        String fileUrl = storageService.createFileServerUrl(user.getId(), file.getName());

        PlacePhoto placePhoto = createPlacePhoto(filePath, fileUrl);

        return placePhotoMapper.placePhotoToPlaceResponse(placePhoto);
    }

    private PlacePhoto createPlacePhoto(String filePath, String fileUrl) {
        PlacePhoto placePhoto = new PlacePhoto();
        placePhoto.setPlacePhotoPath(filePath);
        placePhoto.setPlacePhotoUrl(fileUrl);
        placePhotoRepository.save(placePhoto);
        return placePhoto;
    }

    private PlacePhoto findPlacePhotoByIdOrThrow(Long photoId){
        Optional<PlacePhoto> placePhotoOptional = placePhotoRepository.findById(photoId);
        if (!placePhotoOptional.isPresent()){
            throw new PlacePhotoNotFoundException();
        }

        return placePhotoOptional.get();
    }

    @Override
    public List<PlacePhoto> savePhotos(Place place, MultipartFile[] photos) {
        return null;
    }

}
