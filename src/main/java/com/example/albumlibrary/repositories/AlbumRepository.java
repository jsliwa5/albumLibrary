package com.example.albumlibrary.repositories;

import com.example.albumlibrary.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query(
            value = "SELECT * FROM album ORDER BY RANDOM() LIMIT :n",
            nativeQuery = true
    )
    List<Album> findRandomAlbums(@Param("n") int n);

}
