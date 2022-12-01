package com.example.lesson_10_1.controller;

import com.example.lesson_10_1.entity.Hotel;
import com.example.lesson_10_1.entity.Room;
import com.example.lesson_10_1.payload.RoomDto;
import com.example.lesson_10_1.repository.HotelRepository;
import com.example.lesson_10_1.repository.RoomRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String addRoom(@RequestBody RoomDto roomDto) {
        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setFloor(roomDto.getFloor());
        room.setSize(roomDto.getSize());

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (optionalHotel.isEmpty()) {
            return "Hotel not found";
        }
        room.setHotel(optionalHotel.get());
        roomRepository.save(room);

        return "Room added";
    }

    @GetMapping
    public List<Room> getRooms() {
        List<Room> roomList = roomRepository.findAll();
        return roomList;
    }

    @GetMapping(value = "/{hotelID}")
    public List<Room> getRooms(@PathVariable Integer hotelID, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        Pageable pagingOptions = PageRequest.of(page.orElse(1), pageSize.orElse(20));
        return roomRepository.findAllByHotelId(hotelID, pagingOptions);
    }

    @PutMapping("/{id}")
    public String updateRoom(@PathVariable Integer id, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) {
            return "Room not found";
        }
        Room editingRoom = optionalRoom.get();
        editingRoom.setNumber(roomDto.getNumber());
        editingRoom.setFloor(roomDto.getFloor());
        editingRoom.setSize(roomDto.getSize());

        Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
        if (optionalHotel.isEmpty()) {
            return "Hotel not found";
        }
        editingRoom.setHotel(optionalHotel.get());
        roomRepository.save(editingRoom);

        return "Room edited";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isEmpty()) {
            return "Room not found";
        }
        roomRepository.deleteById(id);

        return "Room deleted";
    }
}
