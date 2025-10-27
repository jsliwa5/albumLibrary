package com.example.albumlibrary.mappers;

import com.example.albumlibrary.dtos.AlbumRequestDto;
import com.example.albumlibrary.dtos.AlbumResponseDto;
import com.example.albumlibrary.dtos.ArtistResponseDto;
import com.example.albumlibrary.models.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AlbumMapper  {

    private final ArtistMapper artistMapper;

    @Autowired
    public AlbumMapper(ArtistMapper artistMapper) {
        this.artistMapper = artistMapper;
    }

    public AlbumResponseDto toDto(Album entity) {
        var dto = new AlbumResponseDto();

        dto.setAlbumId(entity.getAlbum_id());
        dto.setName(entity.getName());
        dto.setImageUrl(entity.getImageUrl());

        if(entity.getArtists() != null){
            Set<ArtistResponseDto> artists = entity.getArtists()
                    .stream()
                    .map(artistMapper::toDto)
                    .collect(Collectors.toSet());

            dto.setArtists(artists);
        }

        return dto;
    }


    public Album toEntity(AlbumRequestDto dto) {
        return null;
    }
}
