package com.example.demo.Controller;

import com.example.demo.Model.Artist;
import com.example.demo.Repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class ArtistController {

    @Autowired
    private ArtistRepository aRp;

    @GetMapping("/artist")
    public String getArtists(Model model) {
        Iterable<Artist> artists = aRp.findAll();
        model.addAttribute("artist", artists);
        return "artist/index";
    }

    @GetMapping("/artist/add")
    public String addArtist(Model model) {
        Artist artist = new Artist();
        model.addAttribute("artist", artist);
        return "artist/add";
    }

    @PostMapping("/artist/add")
    public String artistAdd(@ModelAttribute("artist") Artist artist,
                            @RequestParam("image") MultipartFile file,
                            BindingResult result) {

        if (result.hasErrors()) {
            return "artist/add";
        }

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            artist.setImgURL(fileName);
            String uploadDir = "src/main/resources/static/images/";
            Path uploadPath = Paths.get(uploadDir);

            try (InputStream inputStream = file.getInputStream()) {
                Files.createDirectories(uploadPath);
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                System.out.println("File saving error! " + ex.toString());
                // You might want to add more robust error handling here
            }
        }

        aRp.save(artist);
        return "redirect:/artist";
    }

    @GetMapping("/artist/update/{id}")
    public String updateArtistForm(@PathVariable int id, Model model) {
        Artist artist = aRp.findById(id).orElse(null);
        if (artist == null) {
            return "redirect:/artist"; // handle artist not found
        }
        model.addAttribute("artist", artist);
        return "artist/update";
    }

    @PostMapping("/artist/update/{id}")
    public String updateArtist(@PathVariable int id, @ModelAttribute("artist") Artist artist) {
        artist.setId(id);
        aRp.save(artist);
        return "redirect:/artist";
    }

    @GetMapping("/artist/delete/{id}")
    public String artistDelete(@PathVariable int id) {
        aRp.deleteById(id);
        return "redirect:/artist";
    }
}
