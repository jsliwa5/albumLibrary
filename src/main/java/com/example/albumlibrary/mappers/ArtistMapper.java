package com.example.albumlibrary.mappers;

import com.example.albumlibrary.dtos.ArtistResponseDto;
import com.example.albumlibrary.models.Artist;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {

    public ArtistResponseDto toDto(Artist artist){

        var dto = new ArtistResponseDto();

        dto.setArtistId(artist.getArtist_id());
        dto.setName(artist.getName());

        return dto;

    }

}
