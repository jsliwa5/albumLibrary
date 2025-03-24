package com.example.albumlibrary.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@Entity
@Table
@NoArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long album_id;


    @ManyToMany(mappedBy = "albums", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Artist> artists;

    private String name;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL)
    private Set<Review> reviews;


}
