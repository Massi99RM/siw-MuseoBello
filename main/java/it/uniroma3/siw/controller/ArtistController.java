package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    // Lista di tutti gli artisti (accessibile a tutti gli utenti)
    @GetMapping("/artists")
    public String listArtists(Model model) {
        List<Artist> artists = artistService.findAll();
        model.addAttribute("artists", artists);
        return "artistsList";
    }
}