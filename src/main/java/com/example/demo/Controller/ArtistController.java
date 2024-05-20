package com.example.demo.Controller;

import com.example.demo.Model.Artist;
import com.example.demo.Repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository aRp;

    @GetMapping("/artist")
    private String getArtists(Model model){
        Iterable<Artist> artists = aRp.findAll();
        model.addAttribute("artist", artists);
        return "artist/index" ;
    }

    @GetMapping("/artist/add")
    public String addArtist(Model model){
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        return "artist/add";
    }

    @PostMapping("/artist/add")
    public String artistAdd(@ModelAttribute("artist") Artist artist){
        aRp.save(artist);
        return "redirect:/artist" ;

    }

    @GetMapping("/artist/update/{id}")
    public String updateArtistForm(@PathVariable int id, Model model) {
        Artist artist = aRp.findById(id).get();
        model.addAttribute("artist", artist);
        return "artist/update";
    }

    @PostMapping("/artist/update/{id}")
    public String updateArtist(@PathVariable int id, @ModelAttribute("artist") Artist artist) {
        artist.setId(id);
        aRp.save(artist);
        return "redirect:/artist";
    }



    @GetMapping ("/artist/delete/{id}")
    public String artistDelete(@PathVariable int id){
        aRp.deleteById(id);
        return "redirect:/artist" ;
    }


}
