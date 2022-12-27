package com.example.myAirline.models;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class defining a token which is sent in an email to an app user, who signed up for myAirline.
 * If the token is sent back in time via the provided link (or button), the app user will be enabled.
 * 
 * @since 1.0
 * @author Florin Schikarski
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ConfirmationToken {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private final String token = UUID.randomUUID().toString();

    @Column(nullable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private LocalDateTime expiresAt;

    private LocalDateTime confirmedAt;

    @Column(nullable = false)
    private String appUserEmail; // TODO: put real appUser here


    public ConfirmationToken(LocalDateTime expiresAt, 
                             String appUserEmail) {

        this.expiresAt = expiresAt;
        this.appUserEmail = appUserEmail;
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof ConfirmationToken) 
            return this.token.equals(((ConfirmationToken)(obj)).getToken());

        
        
        return false;
    }
}