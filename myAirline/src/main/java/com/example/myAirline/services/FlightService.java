package com.example.myAirline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirline.models.Flight;
import com.example.myAirline.repositories.FlightRepository;


@Service
public class FlightService {
    
    @Autowired
    private FlightRepository flightRepository;


    public Flight save(Flight flight) {

        return flightRepository.save(flight);
    }
}