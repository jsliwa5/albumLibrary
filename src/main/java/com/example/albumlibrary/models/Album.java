package com.example.albumlibrary.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
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


    @ManyToMany(mappedBy = "albums", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JsonIgnore
    private Set<Artist> artists;

    private String name;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    private Set<Review> reviews;


}
