package com.example.albumlibrary.serivces;

import com.example.albumlibrary.dtos.AlbumResponseDto;
import com.example.albumlibrary.dtos.ReviewRequestDto;
import com.example.albumlibrary.dtos.ReviewResponseDto;
import com.example.albumlibrary.mappers.AlbumMapper;
import com.example.albumlibrary.mappers.ReviewMapper;
import com.example.albumlibrary.models.Album;
import com.example.albumlibrary.models.Review;
import com.example.albumlibrary.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final AlbumMapper albumMapper;
    private final ReviewMapper reviewMapper;


    public AlbumService(AlbumRepository albumRepository,
                        UserService userService, AlbumMapper albumMapper,
                        ReviewMapper reviewMapper) {
        this.albumRepository = albumRepository;
        this.userService = userService;
        this.albumMapper = albumMapper;
        this.reviewMapper = reviewMapper;
    }

    public AlbumResponseDto addAlbum(Album album){
        var albumEntity = albumRepository.save(album);
        return albumMapper.toDto(albumEntity);
    }

    public AlbumResponseDto getAlbumById(Long id) throws RuntimeException{

        var albumEntity = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nie znaleziono albumu"));;

        return albumMapper.toDto(albumEntity);

    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }


    @Transactional
    public Album addReviewToAlbum(ReviewRequestDto reviewRequestDto, Long id, Principal principal) {

        var currentUser = userService.getUserByUsername(principal.getName());
        var review = new Review();

        review.setRating(reviewRequestDto.getRating());
        review.setContent(reviewRequestDto.getContent());
        review.setUser(currentUser);

        var albumToBeUpdated = albumRepository.findById(id).get();
        albumToBeUpdated.getReviews().add(review);

        review.setAlbum(albumToBeUpdated);

        return albumRepository.save(albumToBeUpdated);

    }

    public List<ReviewResponseDto> getAllReviews(Long id){

        var reviews = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nie znaleziono albumu"))
                .getReviews();

        return reviews.stream()
                .map(reviewMapper::toDto)
                .toList();
    }
}
