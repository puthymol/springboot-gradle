package com.softvider.provider.security.service;

import com.softvider.model.BaseResponse;
import com.softvider.provider.security.model.adapter.AuthenticationAdapter;
import com.softvider.provider.security.model.adapter.UserAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {

    BaseResponse execute(UserAdapter userAdapter);

    UserAdapter getUserByUsername(AuthenticationAdapter authenticationAdapter) throws UsernameNotFoundException;
}
