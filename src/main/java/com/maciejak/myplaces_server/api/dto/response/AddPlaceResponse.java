package com.maciejak.myplaces_server.api.dto.response;

public class AddPlaceResponse {

    private Long id;

    public AddPlaceResponse() {
    }

    public AddPlaceResponse(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
