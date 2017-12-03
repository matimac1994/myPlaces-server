package com.maciejak.myplaces_server.api.dto.response;

public class RegistrationResponse {

    private Long userId;


    public RegistrationResponse() {
    }

    public RegistrationResponse(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
