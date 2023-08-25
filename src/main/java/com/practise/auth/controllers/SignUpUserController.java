package com.practise.auth.controllers;

import com.practise.auth.dto.SignUpRequest;
import com.practise.auth.dto.UserDTO;
import com.practise.auth.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SignUpUserController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody SignUpRequest signUpRequest)
    {
        UserDTO createUser= authService.createUser(signUpRequest);
        if(createUser==null)
        {
            return new ResponseEntity<>("User not created!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createUser,HttpStatus.CREATED);
    }
}
