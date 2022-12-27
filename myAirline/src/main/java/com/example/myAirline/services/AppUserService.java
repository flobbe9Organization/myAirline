package com.example.myAirline.services;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.myAirline.config.MailConfig;
import com.example.myAirline.models.AppUser;
import com.example.myAirline.models.ConfirmationToken;
import com.example.myAirline.repositories.AppUserRepository;
import com.example.myAirline.resources.ResourceHandler;


/**
 * Class providing the more complex methods for the AppUser class.
 * 
 * @see AppUser
 * @since 1.0
 * @author Florin Schikarski 
 */
@Service
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenService confirmationTokenService;

    @Autowired
    private MailConfig mailConfig;

    private static final String ACCOUNT_CONFIRMATION_EMAIL = "accountConfirmation.html";

    private static final String ACCOUNT_CONFIRMATION_EMAIL_SUBJECT = "myAirline | Confirm your account";


    public AppUser addNew(AppUser appUser) {

        validate(appUser);

        // case: appUser with this email does already exist
        if (exists(appUser.getEmail())) 
            throw new IllegalStateException("User with email " + appUser.getEmail() + " does already exist.");

        appUser.setAge(appUser.calculateAge());

        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));

        // confirm account
        ConfirmationToken confirmationToken = 
                confirmationTokenService.saveConfirmationToken(new ConfirmationToken(LocalDateTime.now().plusMinutes(15), 
                                                                                     appUser.getEmail()));

        // sending confirmation email
        sendConfirmationEmail(appUser, confirmationToken);

        return appUserRepository.save(appUser);
    }


    /**
     * Making sure the confirmation token is valid, enabling appUser and setting 'confirmed at' of ConfirmationToken.
     * 
     * @param token unique token of ConfirmationToken.
     */
    public void confirmAccount(String token) {

        // set 'confirmed at'
        ConfirmationToken confirmationToken = confirmationTokenService.confirm(token);

        AppUser appUser = getByEmail(confirmationToken.getAppUserEmail());

        appUser.setIsEnabled(true);

        appUserRepository.save(appUser);
    }


    public AppUser getByEmail(String email) {

        return appUserRepository.findByEmail(email).orElseThrow(() -> 
            new IllegalStateException("Could not find user: " + email));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return appUserRepository.findByEmail(username).orElseThrow(() -> 
            new UsernameNotFoundException("Could not find user: " + username));
    }


    public boolean exists(String email) {

        return appUserRepository.existsByEmail(email);
    }


/////////


    /**
     * Checks some attributes of an AppUser.
     *  
     * @param appUser to check.
     * @return true if all attributes are valid.
     * @throws IllegalStateException if one attribute is not valid.
     */
    private boolean validate(AppUser appUser) {

        if (appUser.getBirthday().isAfter(LocalDate.now()))
            throw new IllegalStateException("Birthday cannot be in the future.");

        return true;
    }


    /**
     * Sends confirmation email to a user who tried to sign up. Contains the confirmation token which is 
     * send back via /appUser/confirmAccount.
     * 
     * @param appUser to send the email to.
     * @param confirmationToken with the token to send back.
     * @throws IllegalStateException if the email html file is not found or the email could not be sent.
     */
    private void sendConfirmationEmail(AppUser appUser, ConfirmationToken confirmationToken) {

        // convert html text to String
        String text = ResourceHandler.htmlToString(ResourceHandler.HTML_TEMPLATES_PATH + ACCOUNT_CONFIRMATION_EMAIL, 
                                                   appUser.getFirstName(), 
                                                   confirmationToken.getToken());

        // send email
        new Thread(() -> {
            try {
                mailConfig.send(appUser.getEmail(), ACCOUNT_CONFIRMATION_EMAIL_SUBJECT, text, null);

            } catch (MessagingException e) {
                throw new IllegalStateException(e.getMessage());
            }
        }).start();
    }
}