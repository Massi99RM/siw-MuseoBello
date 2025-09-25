package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
    Room findByName(String name);
}
