package com.example.demo.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="classification")
public class Classification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int classification_id;

    @Column(name="genre", length = 100)
    private String genre;

    @Column(name="release_date")
    private LocalDate release_date;

    @ManyToOne
    @JoinColumn(name="artist_id", nullable = false)
    private com.example.demo.Model.Artist artist;

    @ManyToOne
    @JoinColumn(name="song_id", nullable = false)
    private Song song;







}
