package com.fmi.travelagencyapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseLocationDTO {

    private Long id;
    private String street;
    private String number;
    private String city;
    private String country;
    private String imageUrl;
}
