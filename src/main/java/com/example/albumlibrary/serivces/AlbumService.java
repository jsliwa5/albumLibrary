package com.example.albumlibrary.serivces;

import com.example.albumlibrary.dtos.AlbumResponseDto;
import com.example.albumlibrary.dtos.ReviewRequestDto;
import com.example.albumlibrary.mappers.AlbumMapper;
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

    public AlbumService(AlbumRepository albumRepository, UserService userService, AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.userService = userService;
        this.albumMapper = albumMapper;
    }

    public Album addAlbum(Album album){
        return albumRepository.save(album);
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
}
