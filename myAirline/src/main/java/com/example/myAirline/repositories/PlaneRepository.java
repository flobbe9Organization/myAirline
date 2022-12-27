package com.example.myAirline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.Plane;


/**
 * Interface writing queries for the Plane entity.
 * 
 * @see Plane
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface PlaneRepository extends JpaRepository<Plane, Long> {
    
}