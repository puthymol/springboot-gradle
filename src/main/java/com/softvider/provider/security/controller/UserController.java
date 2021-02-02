package com.softvider.provider.security.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.softvider.config.security.ApplicationSecurityContext;
import com.softvider.model.BaseResponse;
import com.softvider.provider.security.model.adapter.UserAdapter;
import com.softvider.provider.security.service.UserService;
import com.softvider.utils.AppUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "security")
public class UserController extends AppUtil {
    private static final Logger log = LogManager.getLogger(UserController.class);
    private final ApplicationSecurityContext applicationSecurityContext = new ApplicationSecurityContext();
    @Inject private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value = "anonymous/user", method = RequestMethod.GET)
    public BaseResponse AnonymousStudent(@RequestBody JsonNode jsonNode) {
        UserAdapter request = AppUtil.convert(jsonNode, UserAdapter.class);
        return userService.execute(request);
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public BaseResponse getUser(@RequestBody JsonNode jsonNode, HttpServletRequest httpServletRequest) {
        UserAdapter request = AppUtil.convert(jsonNode, UserAdapter.class);
        log.info("Token {}", httpServletRequest.getHeader("authorization"));
        String username =  applicationSecurityContext.authenticatedUser();
        log.info("Username {}", username);
        return userService.execute(request);
    }
}
