package com.example.lesson_10_1.controller;

import com.example.lesson_10_1.entity.Hotel;
import com.example.lesson_10_1.payload.HotelDto;
import com.example.lesson_10_1.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String addHotel(@RequestBody HotelDto hotelDto) {

        Hotel hotel = new Hotel();
        hotel.setName(hotelDto.getName());
        hotelRepository.save(hotel);

        return "Hotel added";
    }

    @GetMapping
    public List<Hotel> getHotels() {
        List<Hotel> hotelList = hotelRepository.findAll();
        return hotelList;
    }

    @PutMapping("/{id}")
    public String updateHotel(@PathVariable Integer id, @RequestBody HotelDto hotelDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            return "Hotel not found";
        }
        Hotel editingHotel = optionalHotel.get();
        editingHotel.setName(hotelDto.getName());
        hotelRepository.save(editingHotel);

        return "Hotel edited";
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(id);
        if (optionalHotel.isEmpty()) {
            return "Hotel not found";
        }
        hotelRepository.deleteById(id);

        return "Hotel deleted";
    }
}
