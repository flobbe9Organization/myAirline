package com.example.myAirline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirline.models.Plane;
import com.example.myAirline.repositories.PlaneRepository;


/**
 * Class providing the more complex methods for the Plane class.
 * 
 * @since 1.0
 * @author Florin Schikarski
 */
@Service
public class PlaneService {

    @Autowired
    private PlaneRepository planeRepository;

    
    public Plane save(Plane plane) {

        return planeRepository.save(plane);
    }
}