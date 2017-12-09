package com.maciejak.myplaces_server.controllers;

import com.maciejak.myplaces_server.MyPlacesServerApplication;
import com.maciejak.myplaces_server.api.dto.request.LoginRequest;
import com.maciejak.myplaces_server.api.dto.response.LoginResponse;
import com.maciejak.myplaces_server.services.UserService;
import com.maciejak.myplaces_server.services.UserSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping(MyPlacesServerApplication.BASE_URL)
public class UserSessionController {

    private UserSessionService userSessionService;

    public UserSessionController(UserSessionService userSessionService) {
        this.userSessionService = userSessionService;
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok(userSessionService.login(loginRequest));
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request, HttpServletResponse response){
        userSessionService.logout(request, response);
    }
}
