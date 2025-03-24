package com.example.albumlibrary.repositories;

import com.example.albumlibrary.models.Album;
import com.example.albumlibrary.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ArtistRepository extends JpaRepository<Artist,Long> {


}
