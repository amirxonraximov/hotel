package com.example.lesson_10_1.repository;

import com.example.lesson_10_1.entity.Room;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoomRepository extends PagingAndSortingRepository<Room, Integer>, JpaRepository<Room, Integer> {

    List<Room> findAllByHotelId(Integer hotelId, Pageable pageable);

}
