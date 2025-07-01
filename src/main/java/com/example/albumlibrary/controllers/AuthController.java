package com.example.albumlibrary.controllers;

import com.example.albumlibrary.dtos.AuthRequestDto;
import com.example.albumlibrary.dtos.AuthResponseDto;
import com.example.albumlibrary.jwt.JwtService;
import com.example.albumlibrary.models.UserEntity;
import com.example.albumlibrary.serivces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping ("/auth")
@RestController
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    @Autowired
    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }


    @PostMapping("/register")
    public UserEntity register(@RequestBody AuthRequestDto authRequestDto){
        return userService.addUser(authRequestDto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto authRequestDto){

        var authenticatedUser = userService.authenticate(authRequestDto);
        var jwt = jwtService.generateToken(authenticatedUser);
        var loginResponse = new AuthResponseDto();
        loginResponse.setToken(jwt);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return loginResponse;
    }



}
