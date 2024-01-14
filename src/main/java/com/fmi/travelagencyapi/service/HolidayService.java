package com.fmi.travelagencyapi.service;

import com.fmi.travelagencyapi.dto.CreateHolidayDTO;
import com.fmi.travelagencyapi.dto.ResponseHolidayDTO;
import com.fmi.travelagencyapi.dto.UpdateHolidayDTO;
import com.fmi.travelagencyapi.entity.Holiday;
import com.fmi.travelagencyapi.entity.Location;
import com.fmi.travelagencyapi.mapper.HolidayMapper;
import com.fmi.travelagencyapi.repository.HolidayRepository;
import com.fmi.travelagencyapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class HolidayService {

    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;
    private final LocationRepository locationRepository;

    @Autowired
    public HolidayService(HolidayRepository holidayRepository, HolidayMapper holidayMapper, LocationRepository locationRepository) {
        this.holidayRepository = holidayRepository;
        this.holidayMapper = holidayMapper;
        this.locationRepository = locationRepository;
    }

    public List<ResponseHolidayDTO> getAllHolidays(){
        List<Holiday> holidays = holidayRepository.findAll();

        List<ResponseHolidayDTO> mappedHolidays = holidays
                .stream()
                .map(holidayMapper::holidayToResponseHolidayDTO)
                .toList();

        return mappedHolidays;
    }

    public ResponseHolidayDTO getHolidayById(Long id) {
        Holiday holiday = holidayRepository.getReferenceById(id);
        ResponseHolidayDTO mappedHoliday = holidayMapper.holidayToResponseHolidayDTO(holiday);
        return mappedHoliday;
    }

    @Transactional
    public Boolean deleteById(Long id) {
        Holiday holiday = holidayRepository.findById(id)
                        .orElseThrow();
        holidayRepository.delete(holiday);
        return Boolean.TRUE;
   }

    @Transactional
    public ResponseHolidayDTO createHoliday(CreateHolidayDTO holidayRequest) {
        Holiday holiday = new Holiday();
        holiday.setTitle(holidayRequest.getTitle());
        holiday.setPrice(holidayRequest.getPrice());
        holiday.setFreeSlots(holidayRequest.getFreeSlots());
        Location location = locationRepository
                .findById(holidayRequest.getLocation())
                .orElseThrow();
        holiday.setLocation(location);
        holiday.setDuration(holidayRequest.getDuration());
        holiday.setStartDate(holidayRequest.getStartDate());
        holidayRepository.save(holiday);
        return holidayMapper.holidayToResponseHolidayDTO(holiday);
    }

    @Transactional
    public ResponseHolidayDTO updateHoliday(UpdateHolidayDTO holidayRequest) {
        Holiday holiday = holidayRepository
                .findById(holidayRequest.getId())
                .orElseThrow();
        holiday.setStartDate(holidayRequest.getStartDate());
        holiday.setTitle(holidayRequest.getTitle());
        holiday.setPrice(holidayRequest.getPrice());
        Location location = locationRepository
                .findById(holidayRequest.getLocation())
                .orElseThrow();
        holiday.setLocation(location);
        holiday.setDuration(holidayRequest.getDuration());
        holiday.setFreeSlots(holidayRequest.getFreeSlots());
        holidayRepository.save(holiday);
        return holidayMapper.holidayToResponseHolidayDTO(holiday);
    }

    public void updateHolidayFreeSlots(Holiday holiday, String operation) {
        if(operation.equals("createReservation")) {
            holiday.setFreeSlots(holiday.getFreeSlots() - 1);
            holidayRepository.save(holiday);
        }
        if(operation.equals("deleteReservation")){
            holiday.setFreeSlots(holiday.getFreeSlots() + 1);
            holidayRepository.save(holiday);
        }
    }
}
