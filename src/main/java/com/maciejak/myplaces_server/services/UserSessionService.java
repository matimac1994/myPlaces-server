package com.maciejak.myplaces_server.services;

import com.maciejak.myplaces_server.api.dto.request.LoginRequest;
import com.maciejak.myplaces_server.api.dto.response.LoginResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface UserSessionService {

    LoginResponse login(LoginRequest loginRequest);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
