package com.example.albumlibrary.mappers;

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


    public Review toEntity(ReviewResponseDto dto) {
        Review review = new Review();
        review.setReview_id(dto.getReviewId());
        review.setRating(dto.getRating());
        review.setContent(dto.getContent());
        return review;
    }
}
