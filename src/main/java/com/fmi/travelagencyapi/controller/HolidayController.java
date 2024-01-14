package com.fmi.travelagencyapi.controller;

import com.fmi.travelagencyapi.dto.CreateHolidayDTO;
import com.fmi.travelagencyapi.dto.ResponseHolidayDTO;
import com.fmi.travelagencyapi.dto.UpdateHolidayDTO;
import com.fmi.travelagencyapi.service.HolidayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("holidays")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseHolidayDTO>> getAllHolidays() {
        List<ResponseHolidayDTO> holidays = holidayService.getAllHolidays();

        return new ResponseEntity<>(holidays, HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<ResponseHolidayDTO> getHolidayById(@PathVariable Long id) {
        ResponseHolidayDTO holidayDTO = holidayService.getHolidayById(id);
        return new ResponseEntity<>(holidayDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseHolidayDTO> createHoliday(@RequestBody CreateHolidayDTO createHolidayDTO) {
        ResponseHolidayDTO createdHoliday = holidayService.createHoliday(createHolidayDTO);
        return new ResponseEntity<>(createdHoliday, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseHolidayDTO> updateHoliday(@RequestBody UpdateHolidayDTO updateHolidayDTO) {
        ResponseHolidayDTO updateHoliday = holidayService.updateHoliday(updateHolidayDTO);
        return new ResponseEntity<>(updateHoliday, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteHolidayById(@PathVariable Long id) {
        Boolean deletion = holidayService.deleteById(id);
        return new ResponseEntity<>(deletion, HttpStatus.OK);
    }
}
