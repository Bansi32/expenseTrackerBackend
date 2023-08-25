package com.practise.auth.services;

import com.practise.auth.dto.SignUpRequest;
import com.practise.auth.dto.UserDTO;
import com.practise.auth.entity.Category;
import com.practise.auth.entity.User;
import com.practise.auth.mapper.UserMapper;
import com.practise.auth.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDTO createUser(SignUpRequest signUpRequest) {
            User user=new User();
            user.setEmail(signUpRequest.getEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));
            user.setName(signUpRequest.getName());
            user.setPhone(signUpRequest.getPhone());
            User createUser=userRepo.save(user);
            return userMapper.fromUsertoUserDto(createUser);
    }

}
