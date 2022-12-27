package com.example.myAirline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirline.models.Airport;
import com.example.myAirline.repositories.AirportRepository;


@Service
public class AirportService {
    
    @Autowired
    private AirportRepository airportRepository;


    public Airport save(Airport airport) {

        return airportRepository.save(airport);
    }
}