package com.example.albumlibrary.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class Track {

    @Id
    private Long track_id;
    private String title;
    private String duration;
    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

}
