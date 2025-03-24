package com.example.albumlibrary.controllers;

import com.example.albumlibrary.dtos.UserDto;
import com.example.albumlibrary.models.UserEntity;
import com.example.albumlibrary.serivces.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserEntity register(@RequestBody UserDto userDto){
        return userService.addUser(userDto);
    }

    @GetMapping("/{username}")
    public UserEntity getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }




}
