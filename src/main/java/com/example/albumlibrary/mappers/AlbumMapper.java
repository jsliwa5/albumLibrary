package com.example.albumlibrary.mappers;

import com.example.albumlibrary.dtos.AlbumRequestDto;
import com.example.albumlibrary.dtos.AlbumResponseDto;
import com.example.albumlibrary.dtos.ReviewResponseDto;
import com.example.albumlibrary.models.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AlbumMapper  {

    private final ReviewMapper reviewMapper;

    @Autowired
    public AlbumMapper(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    public AlbumResponseDto toDto(Album entity) {
        var dto = new AlbumResponseDto();

        dto.setAlbumId(entity.getAlbum_id());
        dto.setName(entity.getName());

        if(entity.getReviews() != null){
            Set<ReviewResponseDto> reviews = entity.getReviews()
                    .stream()
                    .map(reviewMapper::toDto)
                    .collect(Collectors.toSet());

            dto.setReviews(reviews);
        }

        return dto;
    }


    public Album toEntity(AlbumRequestDto dto) {
        return null;
    }
}
