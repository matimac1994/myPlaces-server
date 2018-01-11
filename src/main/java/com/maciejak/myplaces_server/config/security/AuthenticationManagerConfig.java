package com.maciejak.myplaces_server.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import java.util.Arrays;

@Configuration
public class AuthenticationManagerConfig {

    @Bean(name = "userAuthenticationManager")
    @Autowired
    AuthenticationManager authenticationManager(DaoAuthenticationProvider authenticationProvider){
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }


}
