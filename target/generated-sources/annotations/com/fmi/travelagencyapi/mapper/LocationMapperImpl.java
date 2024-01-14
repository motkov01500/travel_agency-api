package com.fmi.travelagencyapi.mapper;

import com.fmi.travelagencyapi.dto.ResponseLocationDTO;
import com.fmi.travelagencyapi.dto.UpdateLocationDTO;
import com.fmi.travelagencyapi.entity.Location;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-14T20:05:41+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 19.0.1 (Oracle Corporation)"
)
@Component
public class LocationMapperImpl implements LocationMapper {

    @Override
    public ResponseLocationDTO locationToResponseLocationDTO(Location location) {
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

    @Override
    public UpdateLocationDTO locationToUpdateLocationDTO(Location location) {
        if ( location == null ) {
            return null;
        }

        UpdateLocationDTO updateLocationDTO = new UpdateLocationDTO();

        updateLocationDTO.setId( location.getId() );
        updateLocationDTO.setStreet( location.getStreet() );
        updateLocationDTO.setNumber( location.getNumber() );
        updateLocationDTO.setCity( location.getCity() );
        updateLocationDTO.setCountry( location.getCountry() );
        updateLocationDTO.setImageUrl( location.getImageUrl() );

        return updateLocationDTO;
    }
}
