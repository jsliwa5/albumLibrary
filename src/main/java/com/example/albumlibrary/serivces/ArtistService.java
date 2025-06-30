package com.example.albumlibrary.serivces;

import com.example.albumlibrary.dtos.AlbumRequestDto;
import com.example.albumlibrary.dtos.ArtistRequestDto;
import com.example.albumlibrary.dtos.ArtistResponseDto;
import com.example.albumlibrary.mappers.ArtistMapper;
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
    private final ArtistMapper artistMapper;

    @Autowired
    public ArtistService(ArtistRepository artistRepository, ArtistMapper artistMapper) {
        this.artistRepository = artistRepository;
        this.artistMapper = artistMapper;
    }

    public ArtistResponseDto addArtist(ArtistRequestDto artist){
        var artistToBeSaved = new Artist();
        artistToBeSaved.setName(artist.getName());

        var artistEntity = artistRepository.save(artistToBeSaved);
        return artistMapper.toDto(artistEntity);
    }

    public List<Artist> getAllArtists(){
        return artistRepository.findAll();
    }

    @Transactional
    public ArtistResponseDto updateArtist(Long id, AlbumRequestDto albumDetails) {

        var artistToBeUpdated = artistRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nie znalezniono artysty"));

        var albumToBeSaved = new Album();
        albumToBeSaved.setName(albumDetails.getName());
        var albums = artistToBeUpdated.getAlbums();

        albums.add(albumToBeSaved);
        artistToBeUpdated.setAlbums(albums);

        var artistEntity = artistRepository.save(artistToBeUpdated);
        return artistMapper.toDto(artistEntity);
    }

    public ArtistResponseDto getArtistById(Long id) {

        var artistEntity = artistRepository.findById(id).get();
        return artistMapper.toDto(artistEntity);
    }
}
