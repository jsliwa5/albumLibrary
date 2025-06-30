package com.example.albumlibrary.serivces;

import com.example.albumlibrary.dtos.ReviewRequestDto;
import com.example.albumlibrary.dtos.ReviewResponseDto;
import com.example.albumlibrary.mappers.AlbumMapper;
import com.example.albumlibrary.mappers.ReviewMapper;
import com.example.albumlibrary.models.Review;
import com.example.albumlibrary.repositories.AlbumRepository;
import com.example.albumlibrary.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

@Service
public class ReviewService {

    private final UserService userService;
    private final AlbumRepository albumRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Autowired
    public ReviewService(UserService userService,
                         AlbumRepository albumRepository,
                         ReviewRepository reviewRepository,
                         ReviewMapper reviewMapper) {
        this.userService = userService;
        this.albumRepository = albumRepository;
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Transactional
    public ReviewResponseDto addReviewToAlbum(ReviewRequestDto dto,
                                              Long albumId,
                                              Principal principal) {

        var user = userService.getUserByUsername(principal.getName());
        var album = albumRepository.findById(albumId)
                .orElseThrow(() -> new EntityNotFoundException("Album not found"));

        var review = new Review();
        review.setRating(dto.getRating());
        review.setContent(dto.getContent());
        review.setAlbum(album);
        review.setUser(user);

        var savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }
}
