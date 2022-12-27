package com.example.myAirline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.Booking;


/**
 * Interface writing queries for the Booking entity.
 * 
 * @see Booking
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    
}