package com.softvider.provider.security.service.impl;

import com.softvider.provider.security.model.adapter.AuthenticationAdapter;
import com.softvider.provider.security.model.adapter.UserAdapter;
import com.softvider.provider.security.service.UserAuthentication;
import com.softvider.provider.security.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private static final Logger log = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    UserService userService;

    @Override
    public UserAuthentication loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthenticationAdapter reqModel = new AuthenticationAdapter();
        reqModel.setUsername(username);
        try {
            UserAdapter userAdapter = userService.getUserByUsername(reqModel);
            return new UserAuthentication(userAdapter.getUsername(), userAdapter.getPassword(), new ArrayList<>(), userAdapter);
        } catch (UsernameNotFoundException e) {
            log.info("Error <= {}", e.getMessage());
            throw e;
        }
    }
}
