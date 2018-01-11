package com.maciejak.myplaces_server.services.Impl;

import com.maciejak.myplaces_server.api.dto.response.TopPlaceResponse;
import com.maciejak.myplaces_server.api.dto.response.TopPlaceResponseList;
import com.maciejak.myplaces_server.api.mappers.TopPlaceMapper;
import com.maciejak.myplaces_server.entity.TopPlace;
import com.maciejak.myplaces_server.entity.TopPlacePhoto;
import com.maciejak.myplaces_server.exception.place.PlaceNotFoundException;
import com.maciejak.myplaces_server.repositories.TopPlacePhotoRepository;
import com.maciejak.myplaces_server.repositories.TopPlaceRepository;
import com.maciejak.myplaces_server.services.TopPlacesService;
import com.maciejak.myplaces_server.utils.TopPlacesParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TopPlacesServiceImpl implements TopPlacesService {

    private TopPlaceRepository topPlaceRepository;
    private TopPlacePhotoRepository topPlacePhotoRepository;
    private TopPlaceMapper topPlaceMapper;

    public TopPlacesServiceImpl(TopPlacePhotoRepository topPlacePhotoRepository,
                                TopPlaceRepository topPlaceRepository,
                                TopPlaceMapper topPlaceMapper) {
        this.topPlaceRepository = topPlaceRepository;
        this.topPlacePhotoRepository = topPlacePhotoRepository;
        this.topPlaceMapper = topPlaceMapper;
    }

    @Override
    public List<TopPlaceResponseList> getTopPlaces() {
        List<TopPlace> topPlaces = topPlaceRepository.findAllByOrderByRankAsc();
        List<TopPlaceResponseList> response = new ArrayList<>();
        for (TopPlace topPlace : topPlaces){
            TopPlaceResponseList topPlaceResponseList = topPlaceMapper.topPlaceToTopPlaceResponseList(topPlace);
            response.add(topPlaceResponseList);
        }

        return response;
    }

    @Override
    public TopPlaceResponse getTopPlaceById(Long topPlaceId) {
        TopPlace topPlace = topPlaceRepository.findOne(topPlaceId);
        if (topPlace == null){
            throw new PlaceNotFoundException();
        }

        return topPlaceMapper.topPlaceToTopPlaceResponse(topPlace);
    }

    @Override
    public void save(TopPlace topPlace) {
        topPlaceRepository.save(topPlace);
    }

    @Override
    public void save(List<TopPlace> topPlaces) {
        topPlaceRepository.save(topPlaces);
    }

    @Override
    public void clear() {
        topPlacePhotoRepository.deleteAll();
        topPlaceRepository.deleteAll();
    }
}
