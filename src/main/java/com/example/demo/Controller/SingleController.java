package com.example.demo.Controller;

import com.example.demo.DTO.SingleView;
import com.example.demo.Model.Artist;
import com.example.demo.Model.Classification;
import com.example.demo.Model.Song;
import com.example.demo.Repository.ArtistRepository;
import com.example.demo.Repository.ClassificationRepository;
import com.example.demo.Repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class SingleController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private ClassificationRepository classificationRepository;

    @GetMapping("/single")
    public String showSingleForm(Model model) {
        Iterable<Classification> classifications = classificationRepository.findAll();
        List<SingleView> singleViews = new ArrayList<>();
        for (Classification cF : classifications) {
            SingleView sv = new SingleView();
            sv.setArtist(cF.getArtist());
            sv.setSong(cF.getSong());
            sv.setRelease_date(cF.getRelease_date());
            sv.setGenre(cF.getGenre());
            sv.setClassification_id(cF.getClassification_id());
            singleViews.add(sv);

        }
        model.addAttribute("sVw", singleViews);
        return "single/index";
    }
    @GetMapping("/single/add")
    public String addSingle(Model model){
        List<Artist> artists = artistRepository.findAll();
        List<Song> songs = songRepository.findAll();

        model.addAttribute("artists", artists);
        model.addAttribute("songs", songs);
        model.addAttribute("classification", new Classification());
        return "/single/add";
    }

    @PostMapping("/single/add")
    public String createSingle(@ModelAttribute("classification") Classification classification) {

        Artist artist = classification.getArtist();
        Song song = classification.getSong();


        Optional<Artist> optionalArtist = artistRepository.findById(artist.getId());
        Optional<Song> optionalSong = songRepository.findById(song.getId());

        if (optionalArtist.isPresent() && optionalSong.isPresent()) {
            artist = optionalArtist.get();
            song = optionalSong.get();

            classification.setArtist(artist);
            classification.setSong(song);

            classificationRepository.save(classification);
        }

        return "redirect:/single";
    }
    @GetMapping("/single/update/{id}")
    public String showUpdateForm(@PathVariable int id, Model model) {
        Classification classification = classificationRepository.findById(id).orElse(null);
        model.addAttribute("classification", classification);
        return "single/update";
    }
    @PostMapping("/single/update/{id}")
    public String updateSingle(@PathVariable("id") int id, @ModelAttribute("classification") Classification updatedClassification) {
        Classification classification = classificationRepository.findById(id).orElse(null);
        if (classification != null) {

            classification.setGenre(updatedClassification.getGenre());
            classification.setRelease_date(updatedClassification.getRelease_date());


            classificationRepository.save(classification);
        }
        return "redirect:/single";
    }


    @GetMapping ("/single/delete/{id}")
    public String deleteSingle(@PathVariable int id) {
        classificationRepository.deleteById(id);
        return "redirect:/single";
    }


}
