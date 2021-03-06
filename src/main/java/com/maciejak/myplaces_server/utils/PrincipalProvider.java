package com.maciejak.myplaces_server.utils;

import com.maciejak.myplaces_server.entity.User;
import com.maciejak.myplaces_server.config.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class PrincipalProvider {

    public static User getUserEntity(){
        return ((CustomUserDetails) SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getPrincipal())
                .getUser();
    }
}
