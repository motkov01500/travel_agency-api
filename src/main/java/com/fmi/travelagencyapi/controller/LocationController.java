package com.fmi.travelagencyapi.controller;

import com.fmi.travelagencyapi.dto.CreateLocationDTO;
import com.fmi.travelagencyapi.dto.ResponseLocationDTO;
import com.fmi.travelagencyapi.dto.UpdateLocationDTO;
import com.fmi.travelagencyapi.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<ResponseLocationDTO>> getAllLocations() {
        List<ResponseLocationDTO> locations = locationService.getLocations();

        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseLocationDTO> getLocationById(@PathVariable Long id) {
        ResponseLocationDTO locationDTO = locationService.getLocationById(id);
        return new ResponseEntity<>(locationDTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseLocationDTO> createLocation(@RequestBody CreateLocationDTO createLocationDTO) {
        ResponseLocationDTO newLocation = locationService.createLocation(createLocationDTO);
        return new ResponseEntity<>(newLocation, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ResponseLocationDTO> updateLocation(@RequestBody UpdateLocationDTO updateLocationDTO) {
        ResponseLocationDTO updateLocation = locationService.updateLocation(updateLocationDTO);
        return new ResponseEntity<>(updateLocation, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteLocationById(@PathVariable Long id) {
        Boolean deletion = locationService.deleteLocation(id);
        return new ResponseEntity<>(deletion, HttpStatus.OK);
    }
}
