package com.example.myAirline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirline.models.Country;
import com.example.myAirline.repositories.CountryRepository;


@Service
public class CountryService {
    
    @Autowired
    private CountryRepository countryRepository;


    public Country save(Country country) {

        return countryRepository.save(country);
    }
}