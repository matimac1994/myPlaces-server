package com.maciejak.myplaces_server.api.dto.response;

public class PlacePhotoResponse {

    Long id;
    String image;
    Long placeId;

    public PlacePhotoResponse() {
    }

    public PlacePhotoResponse(Long id, String image, Long placeId) {
        this.id = id;
        this.image = image;
        this.placeId = placeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }
}
