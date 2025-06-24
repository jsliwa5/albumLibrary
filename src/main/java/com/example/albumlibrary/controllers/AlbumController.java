package com.example.albumlibrary.controllers;

import com.example.albumlibrary.dtos.AlbumRequestDto;
import com.example.albumlibrary.dtos.AlbumResponseDto;
import com.example.albumlibrary.dtos.ReviewRequestDto;
import com.example.albumlibrary.models.Album;
import com.example.albumlibrary.serivces.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

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
    public AlbumResponseDto getAlbumById(@PathVariable Long id){
        return albumService.getAlbumById(id);
    }

    @PostMapping
    public Album addAlbum(@RequestBody Album album) {
        return albumService.addAlbum(album);
    }


    @PostMapping("/{id}/reviews")
    public Album addReview(@RequestBody ReviewRequestDto reviewRequestDto, @PathVariable Long id, Principal principal){
        return albumService.addReviewToAlbum(reviewRequestDto, id, principal);
    }

}
