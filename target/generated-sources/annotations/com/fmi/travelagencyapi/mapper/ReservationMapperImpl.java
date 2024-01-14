package com.fmi.travelagencyapi.mapper;

import com.fmi.travelagencyapi.dto.ResponseHolidayDTO;
import com.fmi.travelagencyapi.dto.ResponseLocationDTO;
import com.fmi.travelagencyapi.dto.ResponseReservationDTO;
import com.fmi.travelagencyapi.entity.Holiday;
import com.fmi.travelagencyapi.entity.Location;
import com.fmi.travelagencyapi.entity.Reservation;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-07T21:20:35+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class ReservationMapperImpl implements ReservationMapper {

    @Override
    public ResponseReservationDTO reservationToResponseReservationDTO(Reservation reservation) {
        if ( reservation == null ) {
            return null;
        }

        ResponseReservationDTO responseReservationDTO = new ResponseReservationDTO();

        responseReservationDTO.setId( reservation.getId() );
        responseReservationDTO.setContactName( reservation.getContactName() );
        responseReservationDTO.setPhoneNumber( reservation.getPhoneNumber() );
        responseReservationDTO.setHoliday( holidayToResponseHolidayDTO( reservation.getHoliday() ) );

        return responseReservationDTO;
    }

    protected ResponseLocationDTO locationToResponseLocationDTO(Location location) {
        if ( location == null ) {
            return null;
        }

        ResponseLocationDTO responseLocationDTO = new ResponseLocationDTO();

        responseLocationDTO.setId( location.getId() );
        responseLocationDTO.setStreet( location.getStreet() );
        responseLocationDTO.setNumber( location.getNumber() );
        responseLocationDTO.setCity( location.getCity() );
        responseLocationDTO.setCountry( location.getCountry() );
        responseLocationDTO.setImageUrl( location.getImageUrl() );

        return responseLocationDTO;
    }

    protected ResponseHolidayDTO holidayToResponseHolidayDTO(Holiday holiday) {
        if ( holiday == null ) {
            return null;
        }

        ResponseHolidayDTO responseHolidayDTO = new ResponseHolidayDTO();

        responseHolidayDTO.setId( holiday.getId() );
        responseHolidayDTO.setTitle( holiday.getTitle() );
        responseHolidayDTO.setStartDate( holiday.getStartDate() );
        responseHolidayDTO.setDuration( holiday.getDuration() );
        responseHolidayDTO.setPrice( holiday.getPrice() );
        responseHolidayDTO.setFreeSlots( holiday.getFreeSlots() );
        responseHolidayDTO.setLocation( locationToResponseLocationDTO( holiday.getLocation() ) );

        return responseHolidayDTO;
    }
}
