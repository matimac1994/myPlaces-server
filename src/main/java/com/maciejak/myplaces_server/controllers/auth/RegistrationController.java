package com.maciejak.myplaces_server.controllers.auth;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.request.RegistrationRequest;
import com.maciejak.myplaces_server.api.dto.response.RegistrationResponse;
import com.maciejak.myplaces_server.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(MyPlacesServerApplication.BASE_URL + "/register")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody RegistrationRequest registrationRequest){
        return ResponseEntity.ok(userService.createNewUser(registrationRequest));
    }

}
