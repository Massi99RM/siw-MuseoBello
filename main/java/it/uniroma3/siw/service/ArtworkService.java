package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Artist;
import it.uniroma3.siw.model.Artwork;
import it.uniroma3.siw.model.Room;
import it.uniroma3.siw.repository.ArtworkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtworkService {

    @Autowired
    private ArtworkRepository artworkRepository;

    public List<Artwork> findAll() {
        return artworkRepository.findAll();
    }

    public Optional<Artwork> findById(Long id) {
        return artworkRepository.findById(id);
    }

    public List<Artwork> findByArtist(Artist artist) {
        return artworkRepository.findByArtist(artist);
    }
    
    public List<Artwork> findByRoom(Room room) {
        return artworkRepository.findByRoom(room);
    }

    public Artwork save(Artwork artwork) {
        return artworkRepository.save(artwork);
    }

    public void deleteById(Long id) {
        artworkRepository.deleteById(id);
    }
}