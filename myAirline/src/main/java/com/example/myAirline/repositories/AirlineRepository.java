package com.example.myAirline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.Airline;


/**
 * Interface writing queries for the Airline entity.
 * 
 * @see Airline
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface AirlineRepository extends JpaRepository<Airline, Long> {
    
}