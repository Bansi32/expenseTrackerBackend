package com.practise.auth.services;

import com.practise.auth.dto.SignUpRequest;
import com.practise.auth.dto.UserDTO;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    UserDTO createUser(SignUpRequest signUpRequest);
}
