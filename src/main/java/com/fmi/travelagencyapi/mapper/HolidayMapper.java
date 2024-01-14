package com.fmi.travelagencyapi.mapper;

import com.fmi.travelagencyapi.dto.ResponseHolidayDTO;
import com.fmi.travelagencyapi.dto.UpdateHolidayDTO;
import com.fmi.travelagencyapi.entity.Holiday;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface HolidayMapper {

    ResponseHolidayDTO holidayToResponseHolidayDTO(Holiday holiday);
}
