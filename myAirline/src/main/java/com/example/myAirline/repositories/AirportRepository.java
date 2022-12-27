package com.example.myAirline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.Airport;


/**
 * Interface writing queries for the Airport entity.
 * 
 * @see Airport
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {
    
}