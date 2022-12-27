package com.example.myAirline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.Country;


/**
 * Interface writing queries for the Country entity.
 * 
 * @see Country
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    
}