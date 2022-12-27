package com.example.myAirline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.Flight;


/**
 * Interface writing queries for the Flight entity.
 * 
 * @see Flight
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    
}