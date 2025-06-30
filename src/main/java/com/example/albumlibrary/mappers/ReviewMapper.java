package com.example.albumlibrary.mappers;

import com.example.albumlibrary.dtos.ReviewRequestDto;
import com.example.albumlibrary.dtos.ReviewResponseDto;
import com.example.albumlibrary.models.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {


    public ReviewResponseDto toDto(Review entity) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.setReviewId(entity.getReview_id());
        dto.setRating(entity.getRating());
        dto.setContent(entity.getContent());
        return dto;
    }


    public Review toEntity(ReviewRequestDto dto) {
        return null;
    }
}
