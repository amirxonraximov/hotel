package com.example.lesson_10_1.repository;

import com.example.lesson_10_1.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}
