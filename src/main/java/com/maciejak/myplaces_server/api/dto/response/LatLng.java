package com.maciejak.myplaces_server.api.dto.response;

public class LatLng {

    private Double latitude;
    private Double longitude;

    public LatLng() {
    }

    public LatLng(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


}
