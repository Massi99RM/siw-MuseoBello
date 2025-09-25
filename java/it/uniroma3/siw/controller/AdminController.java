package it.uniroma3.siw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Artwork;
import it.uniroma3.siw.model.Room;
import it.uniroma3.siw.service.ArtistService;
import it.uniroma3.siw.service.ArtworkService;
import it.uniroma3.siw.service.RoomService;

import java.util.List;
import java.util.Collections;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ArtistService artistService;
	
	@Autowired
	private ArtworkService artworkService;
	
	@Autowired
	private RoomService roomService;

	// Pagina di gestione opere d'arte
	@GetMapping("/manageArtworks")
	public String manageArtworks(Model model) {
		try {
			List<Artist> artists = artistService.findAll();
			List<Room> rooms = roomService.findAll();
			if (rooms == null) {
			    rooms = Collections.emptyList();
			}
			model.addAttribute("rooms", rooms);
			model.addAttribute("newRoom", new Room());
			if (artists == null) {
				artists = Collections.emptyList();
			}
			model.addAttribute("artists", artists);
			model.addAttribute("newArtwork", new Artwork());
			return "admin/manageArtworks";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("artists", Collections.emptyList());
			model.addAttribute("newArtwork", new Artwork());
			model.addAttribute("errorMessage", "Errore nel caricamento della pagina: " + e.getMessage());
			return "admin/manageArtworks";
		}
	}

	// Inserisci nuova opera
	@PostMapping("/insertArtwork")
	public String insertArtwork(@ModelAttribute("newArtwork") Artwork artwork, Model model) {
		try {
	        if (artwork.getArtist() != null) {
	            // Trova automaticamente la sala dell'artista
	            Artist selectedArtist = artistService.findById(artwork.getArtist().getId()).orElse(null);
	            if (selectedArtist != null && selectedArtist.getRoom() != null) {
	                artwork.setRoom(selectedArtist.getRoom());
	                artworkService.save(artwork);
	                model.addAttribute("successMessage", "Opera aggiunta con successo!");
	            } else {
	                model.addAttribute("errorMessage", "L'artista selezionato non ha una sala assegnata!");
	            }
	        } else {
	            model.addAttribute("errorMessage", "Devi selezionare un artista!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("errorMessage", "Errore nell'aggiunta dell'opera: " + e.getMessage());
	    }
		
		// Ricarica la pagina con i dati aggiornati
		List<Artist> artists = artistService.findAll();
		List<Room> rooms = roomService.findAll();
		model.addAttribute("rooms", rooms != null ? rooms : Collections.emptyList());
		model.addAttribute("newRoom", new Room());
		model.addAttribute("artists", artists != null ? artists : Collections.emptyList());
		model.addAttribute("newArtwork", new Artwork());
		return "admin/manageArtworks";
	}

	// Aggiorna opera esistente
	@PostMapping("/updateArtwork/{id}")
	public String updateArtwork(@PathVariable("id") Long id, @ModelAttribute Artwork artwork, Model model) {
		try {
			Artwork existingArtwork = artworkService.findById(id).orElse(null);
			if (existingArtwork != null) {
				existingArtwork.setTitle(artwork.getTitle());
				existingArtwork.setYear(artwork.getYear());
				existingArtwork.setDescription(artwork.getDescription());
				artworkService.save(existingArtwork);
				model.addAttribute("successMessage", "Opera aggiornata con successo!");
			} else {
				model.addAttribute("errorMessage", "Opera non trovata!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Errore nell'aggiornamento dell'opera: " + e.getMessage());
		}
		
		// Ricarica la pagina
		List<Artist> artists = artistService.findAll();
		List<Room> rooms = roomService.findAll();
		model.addAttribute("rooms", rooms != null ? rooms : Collections.emptyList());
		model.addAttribute("newRoom", new Room());
		model.addAttribute("artists", artists != null ? artists : Collections.emptyList());
		model.addAttribute("newArtwork", new Artwork());
		return "admin/manageArtworks";
	}

	// Elimina opera
	@PostMapping("/deleteArtwork/{id}")
	public String deleteArtwork(@PathVariable("id") Long id, Model model) {
		try {
			artworkService.deleteById(id);
			model.addAttribute("successMessage", "Opera eliminata con successo!");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "Errore nell'eliminazione dell'opera: " + e.getMessage());
		}
		
		// Ricarica la pagina
		List<Artist> artists = artistService.findAll();
		model.addAttribute("artists", artists != null ? artists : Collections.emptyList());
		model.addAttribute("newArtwork", new Artwork());
		List<Room> rooms = roomService.findAll();
		model.addAttribute("rooms", rooms != null ? rooms : Collections.emptyList());
		model.addAttribute("newRoom", new Room());
		return "admin/manageArtworks";
	}
	
	// Inserisci nuova sala
	@PostMapping("/insertRoomWithArtwork")
	public String insertRoomWithArtwork(@RequestParam("roomName") String roomName,
	                                  @RequestParam("artistName") String artistName,
	                                  @RequestParam("artistBiography") String artistBiography,
	                                  @RequestParam("artistImageUrl") String artistImageUrl,
	                                  @RequestParam("artworkTitle") String artworkTitle,
	                                  @RequestParam("artworkYear") String artworkYear,
	                                  @RequestParam("artworkDescription") String artworkDescription,
	                                  @RequestParam("artworkImageUrl") String artworkImageUrl,  // QUESTO MANCAVA!
	                                  Model model) {
	    try {
	        // Crea artista (non salvarlo!)
	    	Artist newArtist = new Artist(artistName.trim(), artistBiography, artistImageUrl.trim());

	        // Crea sala (qui scatterà il cascade su artist)
	        Room newRoom = new Room(roomName.trim(), newArtist);

	        // Collego i due lati della relazione
	        newArtist.setRoom(newRoom);

	        // Salvo solo la sala → Hibernate salva anche l’artista
	        Room savedRoom = roomService.save(newRoom);

	        // Crea prima opera (artista e room ora sono managed)
	        Artwork firstArtwork = new Artwork(artworkTitle.trim(), artworkYear, artworkDescription, savedRoom.getArtist(), savedRoom);
	        firstArtwork.setImageUrl(artworkImageUrl.trim()); 
	        artworkService.save(firstArtwork);

	        model.addAttribute("successMessage", "Sala, artista e prima opera creati con successo!");
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Errore: " + e.getMessage());
	    }

	    List<Artist> artists = artistService.findAll();
	    List<Room> rooms = roomService.findAll();
	    model.addAttribute("artists", artists != null ? artists : Collections.emptyList());
	    model.addAttribute("rooms", rooms != null ? rooms : Collections.emptyList());
	    model.addAttribute("newArtwork", new Artwork());
	    model.addAttribute("newRoom", new Room());
	    return "admin/manageArtworks";
	}
	
	// Aggiorna artista esistente
	@PostMapping("/updateArtist/{id}")
	public String updateArtist(@PathVariable("id") Long id, 
	                          @RequestParam("name") String name,
	                          @RequestParam("biography") String biography,
	                          @RequestParam("imageUrl") String imageUrl,
	                          Model model) {
	    try {
	        Artist existingArtist = artistService.findById(id).orElse(null);
	        if (existingArtist != null) {
	            existingArtist.setName(name.trim());
	            existingArtist.setBiography(biography);
	            existingArtist.setImageUrl(imageUrl.trim());
	            artistService.save(existingArtist);
	            model.addAttribute("successMessage", "Artista aggiornato con successo!");
	        } else {
	            model.addAttribute("errorMessage", "Artista non trovato!");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("errorMessage", "Errore nell'aggiornamento dell'artista: " + e.getMessage());
	    }
	    
	    // Ricarica la pagina
	    List<Artist> artists = artistService.findAll();
	    List<Room> rooms = roomService.findAll();
	    model.addAttribute("rooms", rooms != null ? rooms : Collections.emptyList());
	    model.addAttribute("newRoom", new Room());
	    model.addAttribute("artists", artists != null ? artists : Collections.emptyList());
	    model.addAttribute("newArtwork", new Artwork());
	    return "admin/manageArtworks";
	}


	// Elimina sala
	@PostMapping("/deleteRoom/{id}")
	public String deleteRoom(@PathVariable("id") Long id, Model model) {
	    try {
	        roomService.deleteById(id);
	        model.addAttribute("successMessage", "Sala eliminata con successo!");
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Errore nell'eliminazione della sala: " + e.getMessage());
	    }
	    
	    // Ricarica tutto
	    List<Artist> artists = artistService.findAll();
	    List<Room> rooms = roomService.findAll();
	    model.addAttribute("artists", artists != null ? artists : Collections.emptyList());
	    model.addAttribute("rooms", rooms != null ? rooms : Collections.emptyList());
	    model.addAttribute("newArtwork", new Artwork());
	    model.addAttribute("newRoom", new Room());
	    return "admin/manageArtworks";
	}
}