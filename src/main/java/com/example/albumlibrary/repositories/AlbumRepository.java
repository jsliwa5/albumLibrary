package com.example.albumlibrary.repositories;

import com.example.albumlibrary.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {


}
