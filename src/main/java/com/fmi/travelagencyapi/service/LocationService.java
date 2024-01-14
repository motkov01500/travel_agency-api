package com.fmi.travelagencyapi.service;

import com.fmi.travelagencyapi.dto.CreateLocationDTO;
import com.fmi.travelagencyapi.dto.ResponseLocationDTO;
import com.fmi.travelagencyapi.dto.UpdateLocationDTO;
import com.fmi.travelagencyapi.entity.Location;
import com.fmi.travelagencyapi.mapper.LocationMapper;
import com.fmi.travelagencyapi.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final LocationMapper locationMapper;

    @Autowired
    public LocationService(LocationRepository locationRepository, LocationMapper locationMapper) {
        this.locationRepository = locationRepository;
        this.locationMapper = locationMapper;
    }

    public List<ResponseLocationDTO> getLocations() {
        List<Location> locations = locationRepository.findAll();
        List<ResponseLocationDTO> mappedLocations = locations
                .stream()
                .map(locationMapper::locationToResponseLocationDTO)
                .toList();
        return mappedLocations;
    }

    public ResponseLocationDTO getLocationById(Long id) {
        Location location = locationRepository
                .getReferenceById(id);

        ResponseLocationDTO mappedLocation = locationMapper
                .locationToResponseLocationDTO(location);
        return mappedLocation;
    }

    public ResponseLocationDTO createLocation(CreateLocationDTO createLocationRequest) {
        Location location = new Location();
        location.setCity(createLocationRequest.getCity());
        location.setStreet(createLocationRequest.getStreet());
        location.setNumber(createLocationRequest.getNumber());
        location.setCountry(createLocationRequest.getCountry());
        location.setImageUrl(createLocationRequest.getImageUrl());
        locationRepository
                .save(location);
        return locationMapper.locationToResponseLocationDTO(location);
    }

    public ResponseLocationDTO updateLocation(UpdateLocationDTO updateLocationDTO) {
        Location location = locationRepository
                .findById(updateLocationDTO.getId())
                .orElseThrow();
        location.setImageUrl(updateLocationDTO.getImageUrl());
        location.setCity(updateLocationDTO.getCity());
        location.setStreet(updateLocationDTO.getStreet());
        location.setNumber(updateLocationDTO.getNumber());
        location.setCountry(updateLocationDTO.getCountry());
        locationRepository
                .save(location);
        return locationMapper.locationToResponseLocationDTO(location);
    }

    @Transactional
    public Boolean deleteLocation(Long id) {
        Location location = locationRepository
                .findById(id)
                .orElseThrow();
        locationRepository.delete(location);
        return Boolean.TRUE;
    }
}
