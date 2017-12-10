package com.maciejak.myplaces_server.services;

import com.maciejak.myplaces_server.api.dto.request.RegistrationRequest;
import com.maciejak.myplaces_server.api.dto.response.RegistrationResponse;
import com.maciejak.myplaces_server.entity.User;

public interface UserService {

    RegistrationResponse createNewUser(RegistrationRequest registrationRequest);

    User findByUsername(String username);
}
