package com.example.myAirline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.Seat;


/**
 * Interface writing queries for the Seat entity.
 * 
 * @see Seat
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    
}