package io.muic.cs.ooc.lazyliving.backend.security;


import io.muic.cs.ooc.lazyliving.backend.dto.UserDTO;
import io.muic.cs.ooc.lazyliving.backend.entity.User;
import io.muic.cs.ooc.lazyliving.backend.service.UserService;

//import io.muic.cs.ooc.lazyliving.backend.user.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AuthenticationHandler implements AuthenticationProvider{

    private UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationHandler(UserService userService){
        this.userService = userService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        System.out.println("username: " + username);
        String password = authentication.getCredentials().toString();

        System.out.println("password: " + password);
        //UserDTO userDTO;
        User user;
        if ((user = userService.authenticate(username, password)) != null) {
            System.out.println("OK");
            return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        }

            return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }


}
