package com.example.albumlibrary.controllers;

import com.example.albumlibrary.dtos.ReviewDto;
import com.example.albumlibrary.models.Album;
import com.example.albumlibrary.models.Review;
import com.example.albumlibrary.repositories.AlbumRepository;
import com.example.albumlibrary.serivces.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/albums")
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping
    public List<Album> getAllAlbums(){
        return albumService.getAllAlbums();
    }

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }


    @GetMapping("/{id}")
    public Album getAlbumById(@PathVariable Long id){
        return albumService.getAlbumById(id);
    }

    @PostMapping
    public Album addAlbum(@RequestBody Album album) {
        return albumService.addAlbum(album);
    }


    @PostMapping("/{id}/reviews")
    public Album addReview(@RequestBody ReviewDto reviewDto,@PathVariable Long id, Principal principal){
        return albumService.addReviewToAlbum(reviewDto, id, principal);
    }

}
