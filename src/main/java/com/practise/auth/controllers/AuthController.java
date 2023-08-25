package com.practise.auth.controllers;

import com.practise.auth.dto.AuthenticationRequest;
import com.practise.auth.dto.AuthenticationResponse;
import com.practise.auth.services.jwt.UserDetailsServiceImpl;
import com.practise.auth.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authentication")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException
    {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(),authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException e)
        {

            throw new BadCredentialsException("Incorrect username or password!");
        }
        catch(DisabledException disabledException)
        {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,"User is not created. Register User first!");
            return null;
        }
        final UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt= jwtUtil.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }
}
