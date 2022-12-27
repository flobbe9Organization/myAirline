package com.example.myAirline.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.myAirline.models.ConfirmationToken;
import com.example.myAirline.repositories.ConfirmationTokenRepository;


/**
 * Class providing the more complex methods for the ConfirmationToken class.
 * 
 * @since 1.0
 * @author Florin Schikarski
 */
@Service
public class ConfirmationTokenService {
    
    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;


    public ConfirmationToken saveConfirmationToken(ConfirmationToken confirmationToken) {

        validate(confirmationToken);

        return confirmationTokenRepository.save(confirmationToken);
    }


    public ConfirmationToken getByToken(String token) {

        return confirmationTokenRepository.findByToken(token).orElseThrow(() -> 
            new IllegalStateException("Could not find confirmation token with token " + token + "."));
    }


    public ConfirmationToken getByAppUserEmail(String email) {

        return confirmationTokenRepository.findByAppUserEmail(email).orElseThrow(() -> 
            new IllegalStateException("Could not find confirmation token of appUser " + email + "."));
    }


    /**
     * Setting confirmation time if token is neither expired nor already confirmed.
     * 
     * @param confirmationToken to confirm.
     * @return confirmed token.
     * @throws IllegalStateException if token is expired or already confirmed.
     */
    public ConfirmationToken confirm(String token) {

        // checking if token exists
        ConfirmationToken confirmationToken = getByToken(token);

        // checking if token is expired
        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Confirmation token expired.");

        // checking if token is already confirmed
        if (confirmationToken.getConfirmedAt() != null) 
            throw new IllegalStateException("Confirmation token already confirmed.");

        // confirm token
        confirmationToken.setConfirmedAt(LocalDateTime.now());

        return saveConfirmationToken(confirmationToken);
    }


/////// helper methods


    /**
     * Checking attributes of Confirmation token.
     * 
     * @param confirmationToken to check.
     * @return true if all checks were successful.
     * @throws IllegalStateException if one check failed.
     */
    private boolean validate(ConfirmationToken confirmationToken) {

        // checking that token expires after it is created, not before
        if (confirmationToken.getExpiresAt().isBefore(confirmationToken.getCreatedAt()))
            throw new IllegalStateException("Token has to expire after it is created.");

        return true;
    }
}