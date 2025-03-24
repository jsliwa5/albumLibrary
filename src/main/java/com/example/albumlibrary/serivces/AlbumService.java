package com.example.albumlibrary.serivces;

import com.example.albumlibrary.dtos.ReviewDto;
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

    public AlbumService(AlbumRepository albumRepository, UserService userService) {
        this.albumRepository = albumRepository;
        this.userService = userService;
    }

    public Album addAlbum(Album album){
        return albumRepository.save(album);
    }

    public Album getAlbumById(Long id) throws RuntimeException{

        return albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nie znaleziono albumu"));

    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }


    @Transactional
    public Album addReviewToAlbum(ReviewDto reviewDto, Long id, Principal principal) {

        var currentUser = userService.getUserByUsername(principal.getName());
        var review = new Review();

        review.setRating(reviewDto.getRating());
        review.setContent(reviewDto.getContent());
        review.setUser(currentUser);

        var albumToBeUpdated = albumRepository.findById(id).get();
        albumToBeUpdated.getReviews().add(review);

        review.setAlbum(albumToBeUpdated);

        return albumRepository.save(albumToBeUpdated);

    }
}
