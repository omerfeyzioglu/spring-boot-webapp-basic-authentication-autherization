package com.example.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.demo.Model.Artist;
import com.example.demo.Model.Song;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingleView {
    private Integer classification_id;
    private Artist artist;
    private Song song;
    private LocalDate release_date;
    private String genre;

}
