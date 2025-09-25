package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Artwork;
import it.uniroma3.siw.model.Room;
import it.uniroma3.siw.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    List<Artwork> findByArtist(Artist artist);
    List<Artwork> findByRoom(Room room);
}
