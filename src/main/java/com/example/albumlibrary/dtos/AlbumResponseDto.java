package com.example.albumlibrary.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class AlbumResponseDto {
    private long albumId;
    private String name;
    private Set<ReviewResponseDto> reviews;
}
