package com.example.myAirline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.ConfirmationToken;


/**
 * Interface writing queries for the ConfirmationToken entity.
 * 
 * @see ConfirmationToken
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    
    Optional<ConfirmationToken> findByToken(String token);

    Optional<ConfirmationToken> findByAppUserEmail(String appUser);
}