package com.example.albumlibrary.dtos;

import lombok.Data;

@Data
public class AuthResponseDto {

    private String token;
    private long expiresIn;

}
