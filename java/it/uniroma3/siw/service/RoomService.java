package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Room;
import it.uniroma3.siw.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> findAll() {
        return (List<Room>) roomRepository.findAll();
    }

    public Optional<Room> findById(Long id) {
        return roomRepository.findById(id);
    }

    public Room findByName(String name) {
        return roomRepository.findByName(name);
    }

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public void deleteById(Long id) {
        roomRepository.deleteById(id);
    }
}