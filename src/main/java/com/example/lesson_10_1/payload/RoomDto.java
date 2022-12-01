package com.example.lesson_10_1.payload;

import lombok.Data;

@Data
public class RoomDto {

    private String number;

    private String floor;

    private String size;

    private Integer hotelId;
}
