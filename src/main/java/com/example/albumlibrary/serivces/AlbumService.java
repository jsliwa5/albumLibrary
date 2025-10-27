package com.example.albumlibrary.serivces;

import com.example.albumlibrary.dtos.AlbumRequestDto;
import com.example.albumlibrary.dtos.AlbumResponseDto;
import com.example.albumlibrary.mappers.AlbumMapper;
import com.example.albumlibrary.models.Album;
import com.example.albumlibrary.repositories.AlbumRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public AlbumResponseDto addAlbum(AlbumRequestDto albumDto) {

        var albumToBeSaved = albumMapper.toEntity(albumDto);
        albumRepository.save(albumToBeSaved);

        return albumMapper.toDto(albumToBeSaved);
        
    }

    public AlbumResponseDto getAlbumById(Long id) throws RuntimeException {

        var albumEntity = albumRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("nie znaleziono albumu"));


        return albumMapper.toDto(albumEntity);

    }

    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Transactional
    public List<AlbumResponseDto> getRecommendedAlbums(int howMany) {

        List<Album> randomAlbums;

        if(albumRepository.count() <=  howMany ){
            randomAlbums = albumRepository.findAll();
        }
        else{
            randomAlbums = albumRepository.findRandomAlbums(howMany);
        }

        return randomAlbums.stream().map(albumMapper::toDto).toList();
    }
}



