package com.example.myAirline.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.myAirline.models.AppUser;


/**
 * Interface writing queries for the AppUser entity.
 * 
 * @see AppUser
 * @since 1.0
 * @author Florin Schikarski
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    Optional<AppUser> findByEmail(String email);

    Boolean existsByEmail(String email);
}