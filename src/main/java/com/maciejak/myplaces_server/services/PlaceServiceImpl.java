package com.maciejak.myplaces_server.services;

import com.maciejak.myplaces_server.api.dto.request.AddPlaceRequest;
import com.maciejak.myplaces_server.api.dto.request.EditPlaceRequest;
import com.maciejak.myplaces_server.api.dto.request.IdsRequest;
import com.maciejak.myplaces_server.api.dto.response.AddPlaceResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceMapResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceListResponse;
import com.maciejak.myplaces_server.api.dto.response.PlaceResponse;
import com.maciejak.myplaces_server.api.mappers.PlaceMapper;
import com.maciejak.myplaces_server.entity.Place;
import com.maciejak.myplaces_server.entity.PlacePhoto;
import com.maciejak.myplaces_server.entity.User;
import com.maciejak.myplaces_server.exception.place.*;
import com.maciejak.myplaces_server.repositories.PlaceRepository;
import com.maciejak.myplaces_server.repositories.UserRepository;
import com.maciejak.myplaces_server.utils.MapPhotoUtil;
import com.maciejak.myplaces_server.utils.PrincipalProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private PlaceMapper placeMapper;
    private PlaceRepository placeRepository;
    private UserRepository userRepository;
    private PlacePhotoService placePhotoService;

    public PlaceServiceImpl(PlaceMapper placeMapper, PlaceRepository placeRepository, UserRepository userRepository, PlacePhotoService placePhotoService) {
        this.placeMapper = placeMapper;
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
        this.placePhotoService = placePhotoService;
    }

    @Override
    public List<PlaceResponse> getAllPlaces() {
        User u = PrincipalProvider.getUserEntity();
        User user = userRepository.findOne(u.getId());
        return fillListOfPlaceResponse(user.getPlaces());
    }

    @Override
    public List<PlaceResponse> getAllActivePlaces() {
        User u = PrincipalProvider.getUserEntity();
        User user = userRepository.findOne(u.getId());
        return fillListOfPlaceResponse(user.getActivePlaces());
    }

    @Override
    public List<PlaceResponse> getAllArchivedPlaces() {
        User u = PrincipalProvider.getUserEntity();
        User user = userRepository.findOne(u.getId());
        return fillListOfPlaceResponse(user.getArchivePlaces());
    }

    @Override
    public List<PlaceMapResponse> getAllMapPlaces() {
        User u = PrincipalProvider.getUserEntity();
        User user = userRepository.findOne(u.getId());
        return user.getActivePlaces()
                .stream()
                .map(placeMapper::placeToMapPlaceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlaceListResponse> getAllActivePlacesList() {
        User u = PrincipalProvider.getUserEntity();
        User user = userRepository.findOne(u.getId());
        return fillListOfPlaceListResponse(user.getActivePlaces());
    }

    @Override
    public List<PlaceListResponse> getAllArchivedPlaceList() {
        User u = PrincipalProvider.getUserEntity();
        User user = userRepository.findOne(u.getId());
        return fillListOfPlaceListResponse(user.getArchivePlaces());
    }

    @Override
    public PlaceResponse getPlaceById(Long placeId) {
        Place place = placeRepository.findById(placeId);
        if (place == null){
            throw new PlaceNotFoundException();
        }

        return convertToPlaceResponse(place);
    }

    @Override
    public AddPlaceResponse addPlace(AddPlaceRequest addPlaceRequest, MultipartFile[] uploadPhotos) {
        AddPlaceResponse addPlaceResponse = new AddPlaceResponse();

        User user = PrincipalProvider.getUserEntity();

        if (addPlaceRequest.getLatitude() == null || addPlaceRequest.getLongitude() == null){
            throw new WrongPlaceCoordinatesException();
        }

        Place place = placeMapper.addPlaceRequestToPlace(addPlaceRequest);
        place.setCreatedAt(System.currentTimeMillis());
        place.setUser(user);

        List<PlacePhoto> photos = new ArrayList<>();

        if (uploadPhotos != null && uploadPhotos.length > 0){
            photos = placePhotoService.savePhotos(place, uploadPhotos);
        }
        place.setPhotos(photos);

        Place createdPlace = placeRepository.save(place);

        addPlaceResponse.setId(createdPlace.getId());
        return addPlaceResponse;
    }

    @Override
    public void editPlace(EditPlaceRequest editPlaceRequest) {
        Place place = placeRepository.findById(editPlaceRequest.getId());
        if (place == null){
            throw new PlaceNotFoundException();
        }

        place.setTitle(editPlaceRequest.getTitle());
        place.setDescription(editPlaceRequest.getDescription());
        place.setNote(editPlaceRequest.getNote());
        placeRepository.save(place);

    }

    @Override
    public void deletePlace(Long placeId) {
        Place place = placeRepository.findById(placeId);
        if (place == null) {
            throw new PlaceNotFoundException();
        }

        placeRepository.delete(place.getId());
    }

    @Override
    public void restorePlace(Long placeId) {
        Place place = placeRepository.findById(placeId);
        if (place == null){
            throw new PlaceNotFoundException();
        }

        if (place.getDeletedAt() == null){
            throw new PlaceIsNotArchivedException();
        }

        place.setDeletedAt(null);
        placeRepository.save(place);
    }

    @Override
    public void restorePlaces(IdsRequest idsRequest) {
        if(idsRequest.getIds() != null){
            for (Long id : idsRequest.getIds()){
                restorePlace(id);
            }
        } else {
            throw new EmptyPlaceIdsException();
        }

    }

    @Override
    public void deletePlaces(IdsRequest idsRequest) {
        for (Long id : idsRequest.getIds()){
            deletePlace(id);
        }
    }

    @Override
    public void archivePlace(Long placeId) {
        Place place = placeRepository.findById(placeId);
        if (place == null){
            throw new PlaceNotFoundException();
        }

        if (place.getDeletedAt() != null){
            throw new PlaceIsAlreadyArchivedException();
        }

        place.setDeletedAt(System.currentTimeMillis());
        placeRepository.save(place);
    }

    private List<PlaceResponse> fillListOfPlaceResponse(List<Place> places){
        List<PlaceResponse> placesResponse = new ArrayList<>();
        for (Place place : places){
            placesResponse.add(convertToPlaceResponse(place));
        }

        return placesResponse;
    }

    private List<PlaceListResponse> fillListOfPlaceListResponse(List<Place> places){
        List<PlaceListResponse> placeListResponses = new ArrayList<>();
        for (Place place : places){
            placeListResponses.add(convertToPlaceListResponse(place));
        }

        return placeListResponses;
    }

    private PlaceResponse convertToPlaceResponse(Place place){
        PlaceResponse placeResponse = placeMapper.placeToPlaceResponse(place);
        placeResponse.setMapPhoto(new MapPhotoUtil(place.getLatitude(), place.getLongitude()).createUrlForMapImage());
        return placeResponse;
    }

    private PlaceListResponse convertToPlaceListResponse(Place place){
        PlaceListResponse placeListResponse = placeMapper.placeToPlaceListResponse(place);
        placeListResponse.setMapPhoto(new MapPhotoUtil(place.getLatitude(), place.getLongitude()).createUrlForMapImage());
        return placeListResponse;
    }
}
