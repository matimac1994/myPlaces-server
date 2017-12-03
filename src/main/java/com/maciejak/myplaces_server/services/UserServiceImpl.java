package com.maciejak.myplaces_server.services;

import com.maciejak.myplaces_server.api.dto.request.RegistrationRequest;
import com.maciejak.myplaces_server.api.dto.response.RegistrationResponse;
import com.maciejak.myplaces_server.entity.User;
import com.maciejak.myplaces_server.exception.user.EmailAlreadyExistException;
import com.maciejak.myplaces_server.exception.user.NotTheSamePasswordException;
import com.maciejak.myplaces_server.exception.user.UsernameAlreadyExistException;
import com.maciejak.myplaces_server.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegistrationResponse createNewUser(RegistrationRequest registrationRequest) {
        RegistrationResponse registrationResponse = new RegistrationResponse();

        validate(registrationRequest);

        User user = userRepository.save(new User(registrationRequest.getUsername(),
                registrationRequest.getEmail(),
                passwordEncoder.encode(registrationRequest.getPassword())));
        registrationResponse.setUserId(user.getId());
        return registrationResponse;
    }

    private void validate(RegistrationRequest registrationRequest){
        if (userRepository.findByUsername(registrationRequest.getUsername()) != null){
            throw new UsernameAlreadyExistException();
        }

        if (userRepository.findByEmail(registrationRequest.getEmail()) != null){
            throw new EmailAlreadyExistException();
        }

        if (!registrationRequest.getPassword().equals(registrationRequest.getConfirmPassword())){
            throw new NotTheSamePasswordException();
        }
    }

//    private boolean validateEmail(String email){
//        if (email == null) return false;
//        boolean result = true;
//        try {
//        }
//    }
}
