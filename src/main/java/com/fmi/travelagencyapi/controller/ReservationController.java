package com.fmi.travelagencyapi.controller;

import com.fmi.travelagencyapi.dto.CreateReservationDTO;
import com.fmi.travelagencyapi.dto.ResponseReservationDTO;
import com.fmi.travelagencyapi.dto.UpdateReservationDTO;
import com.fmi.travelagencyapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseReservationDTO>> getAllReservations() {
        List<ResponseReservationDTO> allReservations = reservationService
                .getAllReservations();
        return new ResponseEntity<>(allReservations, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseReservationDTO> getReservationById(@PathVariable Long id) {
        ResponseReservationDTO reservationDTO = reservationService.getReservationById(id);
        return new ResponseEntity<>(reservationDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseReservationDTO> createReservation(@RequestBody CreateReservationDTO createReservationDTO) {
        ResponseReservationDTO newReservation = reservationService.createReservation(createReservationDTO);
        return new ResponseEntity<>(newReservation, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseReservationDTO> updateReservation(@RequestBody UpdateReservationDTO updateReservationDTO) {
        ResponseReservationDTO updatedReservation = reservationService.updateReservation(updateReservationDTO);
        return new ResponseEntity<>(updatedReservation, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteReservation(@PathVariable Long id) {
        Boolean deletion = reservationService.deleteById(id);
        return new ResponseEntity<>(deletion, HttpStatus.OK);
    }
}