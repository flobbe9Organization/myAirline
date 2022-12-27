package com.example.myAirline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirline.models.City;
import com.example.myAirline.repositories.CityRepository;


/**
 * Class providing the more complex methods for the City class.
 * 
 * @see City
 * @since 1.0
 * @author Florin Schikarski 
 */
@Service
public class CityService {
    
    @Autowired
    private CityRepository cityRepository;


    public City save(City city) {

        return cityRepository.save(city);
    }
}