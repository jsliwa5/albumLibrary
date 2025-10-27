package com.example.albumlibrary.controllers;

import com.example.albumlibrary.dtos.AlbumRequestDto;
import com.example.albumlibrary.dtos.AlbumResponseDto;
import com.example.albumlibrary.dtos.ReviewRequestDto;
import com.example.albumlibrary.dtos.ReviewResponseDto;
import com.example.albumlibrary.models.Album;
import com.example.albumlibrary.serivces.AlbumService;
import com.example.albumlibrary.serivces.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;
    private final ReviewService reviewService;

    @GetMapping
    public List<Album> getAllAlbums(){
        return albumService.getAllAlbums();
    }

    @Autowired
    public AlbumController(AlbumService albumService, ReviewService reviewService) {
        this.albumService = albumService;
        this.reviewService = reviewService;
    }


    @GetMapping("/{id}")
    public AlbumResponseDto getAlbumById(@PathVariable Long id){
        return albumService.getAlbumById(id);
    }

    @PostMapping
    public AlbumResponseDto addAlbum(@RequestBody AlbumRequestDto albumDto) {
        return albumService.addAlbum(albumDto);
    }


    @PostMapping("/{id}/reviews")
    public ReviewResponseDto addReview(@RequestBody ReviewRequestDto reviewRequestDto, @PathVariable Long id, Principal principal){
        return reviewService.addReviewToAlbum(reviewRequestDto, id, principal);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewResponseDto> getReviews(@PathVariable Long id){
        return reviewService.getReviewsForAlbum(id);
    }

    @GetMapping("/recommended")
    public List<AlbumResponseDto> getRecommendedAlbums(@RequestParam int howMany){
        return albumService.getRecommendedAlbums(howMany);
    }

}
