package com.fmi.travelagencyapi.service;

import com.fmi.travelagencyapi.dto.CreateReservationDTO;
import com.fmi.travelagencyapi.dto.ResponseReservationDTO;
import com.fmi.travelagencyapi.dto.UpdateReservationDTO;
import com.fmi.travelagencyapi.entity.Holiday;
import com.fmi.travelagencyapi.entity.Reservation;
import com.fmi.travelagencyapi.mapper.ReservationMapper;
import com.fmi.travelagencyapi.repository.HolidayRepository;
import com.fmi.travelagencyapi.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HolidayRepository holidayRepository;
    private final HolidayService holidayService;
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, HolidayRepository holidayRepository, HolidayService holidayService, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.holidayRepository = holidayRepository;
        this.holidayService = holidayService;
        this.reservationMapper = reservationMapper;
    }

    public List<ResponseReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        List<ResponseReservationDTO> mappedReservations = reservations
                .stream()
                .map(reservationMapper::reservationToResponseReservationDTO)
                .toList();
        return mappedReservations;
    }

    public ResponseReservationDTO getReservationById(Long id) {
        Reservation reservation = reservationRepository.getReferenceById(id);
        ResponseReservationDTO mappedReservation = reservationMapper.reservationToResponseReservationDTO(reservation);
        return mappedReservation;
    }

    @Transactional
    public ResponseReservationDTO createReservation(CreateReservationDTO createReservationDTO) {
        Reservation reservation = new Reservation();
        reservation.setContactName(createReservationDTO.getContactName());
        reservation.setPhoneNumber(createReservationDTO.getPhoneNumber());
        Holiday holiday = holidayRepository
                .findById(createReservationDTO.getHoliday())
                .orElseThrow();
        reservation.setHoliday(holiday);
        reservationRepository.save(reservation);
        holidayService.updateHolidayFreeSlots(holiday, "createReservation");
        return reservationMapper.reservationToResponseReservationDTO(reservation);
    }

    @Transactional
    public ResponseReservationDTO updateReservation(UpdateReservationDTO updateReservationDTO) {
        Reservation reservation = reservationRepository
                .findById(updateReservationDTO.getId())
                .orElseThrow();
        reservation.setPhoneNumber(updateReservationDTO.getPhoneNumber());
        reservation.setContactName(updateReservationDTO.getContactName());
        Holiday holiday = holidayRepository
                .findById(updateReservationDTO.getHoliday())
                .orElseThrow();
        reservation.setHoliday(holiday);
        reservationRepository.save(reservation);
        return reservationMapper.reservationToResponseReservationDTO(reservation);
    }

    public Boolean deleteById(Long id) {
        Reservation reservation = reservationRepository
                .findById(id)
                .orElseThrow();
        reservationRepository.delete(reservation);
        holidayService.updateHolidayFreeSlots(reservation.getHoliday(), "deleteReservation");
        return Boolean.TRUE;
    }
}
