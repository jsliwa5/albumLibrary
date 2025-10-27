package com.example.albumlibrary.dtos;

import com.example.albumlibrary.models.Artist;
import lombok.Data;

import java.util.List;

@Data
public class AlbumRequestDto {

    public String name;
    public List<Long> artistsIds;
    public String imageUrl;

}
