package com.softvider.provider.security.service;

import com.softvider.provider.security.model.adapter.UserAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class UserAuthentication extends User {

    private final UserAdapter appUser;

    public UserAuthentication(String username, String password, Collection<? extends GrantedAuthority> authorities, UserAdapter appUser) {
        super(username, password, authorities);
        this.appUser = appUser;
    }

    public UserAdapter getAppUser() {
        return appUser;
    }
}
