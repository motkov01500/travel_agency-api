package com.fmi.travelagencyapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreateHolidayDTO {

    private Long location;
    private String title;
    private LocalDate startDate;
    private int duration;
    private Double price;
    private int freeSlots;
}
