package com.prajwal.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prajwal.jwt.entity.JwtRequest;
import com.prajwal.jwt.entity.JwtResponse;
import com.prajwal.jwt.service.JwtService;

@RestController

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
