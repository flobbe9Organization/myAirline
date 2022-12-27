package com.example.myAirline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.Row;


/**
 * Interface writing queries for the Row entity.
 * 
 * @see Row
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface RowRepository extends JpaRepository<Row, Long> {
    
}