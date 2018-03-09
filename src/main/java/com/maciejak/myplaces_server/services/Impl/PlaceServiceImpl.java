package com.maciejak.myplaces_server.services.Impl;

import com.maciejak.myplaces_server.api.dto.request.AddPlaceRequest;
import com.maciejak.myplaces_server.api.dto.request.EditPlaceRequest;
import com.maciejak.myplaces_server.api.dto.request.IdsRequest;
import com.maciejak.myplaces_server.api.dto.response.*;
import com.maciejak.myplaces_server.api.mappers.PlaceMapper;
import com.maciejak.myplaces_server.entity.Place;
import com.maciejak.myplaces_server.entity.PlacePhoto;
import com.maciejak.myplaces_server.entity.User;
import com.maciejak.myplaces_server.exception.place.*;
import com.maciejak.myplaces_server.repositories.PlacePhotoRepository;
import com.maciejak.myplaces_server.repositories.PlaceRepository;
import com.maciejak.myplaces_server.repositories.UserRepository;
import com.maciejak.myplaces_server.services.PlacePhotoService;
import com.maciejak.myplaces_server.services.PlaceService;
import com.maciejak.myplaces_server.services.StorageService;
import com.maciejak.myplaces_server.utils.MapPhotoUtil;
import com.maciejak.myplaces_server.utils.PrincipalProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlaceServiceImpl implements PlaceService {

    private final PlaceMapper placeMapper;
    private final PlaceRepository placeRepository;
    private final PlacePhotoRepository placePhotoRepository;
    private final UserRepository userRepository;
    private final StorageService storageService;

    public PlaceServiceImpl(PlaceMapper placeMapper, PlaceRepository placeRepository,
                            PlacePhotoRepository placePhotoRepository,
                            UserRepository userRepository,
                            StorageService storageService) {
        this.placeMapper = placeMapper;
        this.placeRepository = placeRepository;
        this.placePhotoRepository = placePhotoRepository;
        this.userRepository = userRepository;
        this.storageService = storageService;
    }

    @Override
    public List<PlaceResponse> getAllPlaces() {
        User u = PrincipalProvider.getUserEntity();
        List<Place> places = placeRepository.findAllByUser(u);
        return fillListOfPlaceResponse(places);
    }

    @Override
    public List<PlaceResponse> getAllActivePlaces() {
        User u = PrincipalProvider.getUserEntity();
        List<Place> places = placeRepository.findAllActive(u.getId());
        return fillListOfPlaceResponse(places);
    }

    @Override
    public List<PlaceResponse> getAllArchivedPlaces() {
        User u = PrincipalProvider.getUserEntity();
        List<Place> places = placeRepository.findAllArchived(u.getId());
        return fillListOfPlaceResponse(places);
    }

    @Override
    public List<PlaceMapResponse> getAllMapPlaces() {
        User u = PrincipalProvider.getUserEntity();
        List<Place> places = placeRepository.findAllActive(u.getId());
        return places
                .stream()
                .map(placeMapper::placeToMapPlaceResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<PlaceListResponse> getAllActivePlacesList() {
        User u = PrincipalProvider.getUserEntity();
        List<Place> places = placeRepository.findAllActive(u.getId());
        return fillListOfPlaceListResponse(places);
    }

    @Override
    public List<PlaceListResponse> getAllArchivedPlaceList() {
        User u = PrincipalProvider.getUserEntity();
        List<Place> places = placeRepository.findAllArchived(u.getId());
        return fillListOfPlaceListResponse(places);
    }

    @Override
    public PlaceResponse getPlaceById(Long placeId) {
        return convertToPlaceResponse(findByIdOrThrow(placeId));
    }

    @Override
    public AddPlaceResponse addPlace(AddPlaceRequest addPlaceRequest) {
        AddPlaceResponse addPlaceResponse = new AddPlaceResponse();

        User user = PrincipalProvider.getUserEntity();

        if (addPlaceRequest.getLatitude() == null || addPlaceRequest.getLongitude() == null){
            throw new WrongPlaceCoordinatesException();
        }

        Place place = placeMapper.addPlaceRequestToPlace(addPlaceRequest);
        place.setCreatedAt(System.currentTimeMillis());
        place.setUser(user);

        addPlacePhotosToPlace(place, addPlaceRequest);

        Place createdPlace = placeRepository.save(place);

        addPlaceResponse.setId(createdPlace.getId());
        return addPlaceResponse;
    }

    private void addPlacePhotosToPlace(Place place, AddPlaceRequest addPlaceRequest) {
        if (addPlaceRequest.getPlacePhotosIds() != null
                && addPlaceRequest.getPlacePhotosIds().size() > 0){
            List<PlacePhoto> photos = placePhotoRepository.findAllById(addPlaceRequest.getPlacePhotosIds());
            if (photos != null){
                place.setPhotos(photos);
                photos.forEach(placePhoto -> placePhoto.setPlace(place));
            }
        }
    }

    @Override
    public void editPlace(EditPlaceRequest editPlaceRequest) {
        Place place = findByIdOrThrow(editPlaceRequest.getId());

        place.setTitle(editPlaceRequest.getTitle());
        place.setDescription(editPlaceRequest.getDescription());
        place.setNote(editPlaceRequest.getNote());
        manageEditedPlacePhotos(place, editPlaceRequest.getPhotos());
        placeRepository.save(place);

    }

    @Override
    public void deletePlace(Long placeId) {
        Place place = findByIdOrThrow(placeId);

        List<PlacePhoto> photos = place.getPhotos();
        photos.forEach(placePhoto -> storageService.deletePhotoByPath(placePhoto.getPlacePhotoPath()));
        placePhotoRepository.deleteAll(photos);
        placeRepository.delete(place);
    }

    @Override
    public void restorePlace(Long placeId) {
        Place place = findByIdOrThrow(placeId);
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
        Place place = findByIdOrThrow(placeId);
        if (place.getDeletedAt() != null){
            throw new PlaceIsAlreadyArchivedException();
        }

        place.setDeletedAt(System.currentTimeMillis());
        placeRepository.save(place);
    }

    private Place findByIdOrThrow(Long placeId){
        Optional<Place> placeOptional = placeRepository.findById(placeId);
        if (!placeOptional.isPresent()){
            throw new PlaceNotFoundException();
        }

        return placeOptional.get();
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

    private void manageEditedPlacePhotos(Place place, List<PlacePhotoResponse> photos) {
        List<PlacePhoto> placePhotos = place.getPhotos();
        List<Long> idsOfPhotosFromRequest = photos.stream().map(PlacePhotoResponse::getId).collect(Collectors.toList());
        List<PlacePhoto> placePhotosFromRequest = placePhotoRepository.findAllById(idsOfPhotosFromRequest);

        for (PlacePhoto placePhoto : placePhotosFromRequest){
            if (placePhoto.getPlace() == null){
                placePhoto.setPlace(place);
            }
        }

        for (PlacePhoto placePhoto : placePhotos){
            if (!placePhotosFromRequest.contains(placePhoto)){
                storageService.deletePhotoByPath(placePhoto.getPlacePhotoPath());
                placePhotoRepository.delete(placePhoto);
            }
        }
        place.setPhotos(placePhotosFromRequest);
    }
}
