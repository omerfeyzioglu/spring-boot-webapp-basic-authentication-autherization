package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="artist")
public class Artist {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="name", length=100)
    private String name;

    @Column(name="birthdate")
    private LocalDate birth_date;

    @Column(name="country",length = 100)
    private String country;

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL)
    private List<Classification> classifications;
}
