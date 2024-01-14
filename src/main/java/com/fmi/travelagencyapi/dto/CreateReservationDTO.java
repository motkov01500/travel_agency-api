package com.fmi.travelagencyapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReservationDTO {

    private Long holiday;
    private String contactName;
    private String phoneNumber;
}
