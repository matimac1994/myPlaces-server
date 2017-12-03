package com.maciejak.myplaces_server.api.dto.request;

import java.util.List;

public class PlaceIdsRequest {

    private List<Long> placeIds;

    public PlaceIdsRequest() {
    }

    public PlaceIdsRequest(List<Long> placeIds) {
        this.placeIds = placeIds;
    }

    public List<Long> getPlaceIds() {
        return placeIds;
    }

    public void setPlaceIds(List<Long> placeIds) {
        this.placeIds = placeIds;
    }
}
