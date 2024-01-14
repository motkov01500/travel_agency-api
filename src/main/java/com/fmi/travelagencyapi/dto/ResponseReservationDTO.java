package com.fmi.travelagencyapi.dto;

import com.fmi.travelagencyapi.entity.Holiday;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseReservationDTO {

    private Long id;
    private String contactName;
    private String phoneNumber;
    private ResponseHolidayDTO holiday;
}
