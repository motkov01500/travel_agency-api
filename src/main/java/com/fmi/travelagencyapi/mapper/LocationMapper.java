package com.fmi.travelagencyapi.mapper;

import com.fmi.travelagencyapi.dto.ResponseLocationDTO;
import com.fmi.travelagencyapi.dto.UpdateLocationDTO;
import com.fmi.travelagencyapi.entity.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    ResponseLocationDTO locationToResponseLocationDTO(Location location);

    UpdateLocationDTO locationToUpdateLocationDTO(Location location);
}
