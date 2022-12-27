package com.example.myAirline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirline.models.Airline;
import com.example.myAirline.repositories.AirlineRepository;


/**
 * Class providing the more complex methods for the Airline class.
 * 
 * @see Airline
 * @since 1.0
 * @author Florin Schikarski
 */
@Service
public class AirlineService {
    
    @Autowired
    private AirlineRepository airlineRepository;


    public Airline save(Airline airline) {

        return airlineRepository.save(airline);
    }
}