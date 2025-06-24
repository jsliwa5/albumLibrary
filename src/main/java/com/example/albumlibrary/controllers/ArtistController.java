package com.example.albumlibrary.controllers;

import com.example.albumlibrary.dtos.AlbumRequestDto;
import com.example.albumlibrary.dtos.ArtistRequestDto;
import com.example.albumlibrary.models.Artist;
import com.example.albumlibrary.serivces.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping
    public List<Artist> getAllArtists(){
        return artistService.getAllArtists();
    }

    @GetMapping("/{id}")
    public Artist getArtistById(@PathVariable Long id){
        var artist = artistService.getArtistById(id);

        System.out.println(artist);

        return artist;
    }

    @PostMapping
    public Artist addArtist(@RequestBody ArtistRequestDto artist){
        return artistService.addArtist(artist);
    }

    @PutMapping("/{id}")
    public Artist assignNewAlbumToArtist(@PathVariable Long id, @RequestBody AlbumRequestDto album) throws IOException {
        return artistService.updateArtist(id, album);
    }

}
