package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Room;
import it.uniroma3.siw.service.RoomService;
import it.uniroma3.siw.service.ArtworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class RoomController {

    @Autowired
    private RoomService roomService;
   
    
    @Autowired
    private ArtworkService artworkService;

    // Lista di tutte le sale
    @GetMapping("/rooms")
    public String listRooms(Model model) {
        List<Room> rooms = roomService.findAll();
        model.addAttribute("rooms", rooms);
        return "roomsList";
    }

    // Visualizza le opere di una sala
    @GetMapping("/rooms/{id}")
    public String roomDetails(@PathVariable("id") Long id, Model model) {
        Room room = roomService.findById(id).orElse(null);
        if (room == null) {
            return "redirect:/rooms";
        }
        model.addAttribute("room", room);
        
        // Trova le opere di questa sala
        model.addAttribute("artworks", artworkService.findByRoom(room));
        
        return "roomDetails";
    }
}