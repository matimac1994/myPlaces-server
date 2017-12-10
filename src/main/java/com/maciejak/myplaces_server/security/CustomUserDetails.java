package com.maciejak.myplaces_server.security;

import com.maciejak.myplaces_server.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User{
    private User user;

    public CustomUserDetails(User userEntity, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getUsername(), userEntity.getPassword(), authorities);
        this.user = userEntity;
    }

    public User getUser() {
        return user;
    }
}
