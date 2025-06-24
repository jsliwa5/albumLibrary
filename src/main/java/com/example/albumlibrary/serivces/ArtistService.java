package com.example.albumlibrary.serivces;

import com.example.albumlibrary.dtos.AlbumRequestDto;
import com.example.albumlibrary.dtos.ArtistRequestDto;
import com.example.albumlibrary.models.Album;
import com.example.albumlibrary.models.Artist;
import com.example.albumlibrary.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final AlbumService albumService;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, AlbumService albumService) {
        this.artistRepository = artistRepository;
        this.albumService = albumService;
    }

    public Artist addArtist(ArtistRequestDto artist){
        var artistToBeSaved = new Artist();
        artistToBeSaved.setName(artist.getName());

        return artistRepository.save(artistToBeSaved);
    }

    public List<Artist> getAllArtists(){
        return artistRepository.findAll();
    }

    @Transactional
    public Artist updateArtist(Long id, AlbumRequestDto albumDetails) {

        var artistToBeUpdated = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nie znalezniono artysty"));

        var albumToBeSaved = new Album();

        albumToBeSaved.setName(albumDetails.getName());
        //var album = albumService.addAlbum(albumToBeSaved);

        var albums = artistToBeUpdated.getAlbums();
        albums.add(albumToBeSaved);

        artistToBeUpdated.setAlbums(albums);

        return artistRepository.save(artistToBeUpdated);


    }

    public Artist getArtistById(Long id) {

        var artist = artistRepository.findById(id).get();
        //System.out.println(artist.getAlbums());

        return artist;
    }
}
