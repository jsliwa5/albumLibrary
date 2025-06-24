package com.example.albumlibrary.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewResponseDto {
    private Long reviewId;
    private int rating;
    private String content;


}
