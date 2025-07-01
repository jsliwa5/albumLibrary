package com.example.albumlibrary.serivces;

import com.example.albumlibrary.dtos.AlbumResponseDto;
import com.example.albumlibrary.mappers.AlbumMapper;
import com.example.albumlibrary.models.Album;
import com.example.albumlibrary.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;


    public AlbumService(AlbumRepository albumRepository,
                        AlbumMapper albumMapper) {
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
    }

    public AlbumResponseDto addAlbum(Album album) {
        var albumEntity = albumRepository.save(album);
        return albumMapper.toDto(albumEntity);
    }

    public AlbumResponseDto getAlbumById(Long id) throws RuntimeException {

        var albumEntity = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nie znaleziono albumu"));


        return albumMapper.toDto(albumEntity);

    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

}



