package com.example.myAirline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.City;


/**
 * Interface writing queries for the City entity.
 * 
 * @see City
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    
}