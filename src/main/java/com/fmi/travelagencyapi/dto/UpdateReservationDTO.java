package com.fmi.travelagencyapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateReservationDTO {

    private Long id;
    private String contactName;
    private String phoneNumber;
    private Long holiday;
}
