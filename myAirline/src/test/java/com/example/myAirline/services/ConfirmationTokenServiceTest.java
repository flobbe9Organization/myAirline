package com.example.myAirline.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.myAirline.enums.AppUserRole;
import com.example.myAirline.models.AppUser;
import com.example.myAirline.models.ConfirmationToken;


@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
public class ConfirmationTokenServiceTest {

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private AppUserService appUserService;

    private AppUser appUser = new AppUser("florin.schikarski@gmail.com",
                                        "password",
                                        AppUserRole.USER,
                                        "Florin",
                                        "Schaikarski",
                                        LocalDate.of(2001, 02, 12));


    @Test
    @Order(1)
    void testSaveConfirmationToken() {
        
        // adding appUser 
        appUserService.addNew(appUser);

        ConfirmationToken confirmationToken = new ConfirmationToken(LocalDateTime.now().plusMinutes(15), 
                                                                    "florin.schikarski@gmail.com");

        assertEquals(confirmationToken, confirmationTokenService.saveConfirmationToken(confirmationToken));
    }

    
    @Test
    void testGetByToken() {

        ConfirmationToken confirmationToken = confirmationTokenService.saveConfirmationToken(new ConfirmationToken(LocalDateTime.now().plusMinutes(15), 
                                                                                                                   "florin.schikarski@gmail.com"));

        assertEquals(confirmationToken, confirmationTokenService.getByToken(confirmationToken.getToken()));
    }
    
    
    @Test
    void testConfirm() {

        ConfirmationToken confirmationToken = confirmationTokenService.saveConfirmationToken(new ConfirmationToken(LocalDateTime.now().plusMinutes(15), 
                                                                                                                   "florin.schikarski@gmail.com"));

        ConfirmationToken confirmedToken = confirmationTokenService.confirm(confirmationToken.getToken());

        assertNotEquals(null, confirmedToken.getConfirmedAt());
    }
}