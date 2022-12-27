package com.example.myAirline.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.myAirline.enums.AppUserRole;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class defining a user of myAirline. 
 * <p>
 * Implements UserDetails for spring security.
 * 
 * @since 1.0
 * @author Florin Schikarski
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
public class AppUser implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String email;

    @NotEmpty
    @Length(min = 5)
    private String password;

    @Enumerated(EnumType.STRING)
    private AppUserRole role;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotNull
    private LocalDate birthday;

    private Integer age;

    @OneToMany(fetch = FetchType.LAZY, 
               cascade = CascadeType.ALL,
               mappedBy = "appUser")
    @JsonManagedReference
    @NotNull
    private List<Booking> bookings = new LinkedList<>();

    @NotNull
    private Boolean isAccountNonExpired = true;

    @NotNull
    private Boolean isAccountNonLocked = true;

    @NotNull
    private Boolean isCredentialsNonExpired = true;

    @NotNull
    private Boolean isEnabled = false;


    public AppUser(String email, 
                   String password, 
                   AppUserRole role,
                   String firstName, 
                   String lastName, 
                   LocalDate birthday) {

        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.age = calculateAge();
    }
    
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return this.role.getGrantedAuthorities();
    }


    @Override
    public String getUsername() {

        return this.email;
    }


    @Override
    public boolean isAccountNonExpired() {

        return this.isAccountNonExpired;
    }


    @Override
    public boolean isAccountNonLocked() {

        return this.isAccountNonLocked;
    }


    @Override
    public boolean isCredentialsNonExpired() {

        return this.isCredentialsNonExpired;
    }

    
    @Override
    public boolean isEnabled() {

        return this.isEnabled;
    }


    @Override
    public String toString() {

        return this.email;
    }


    @Override
    public boolean equals(Object obj) {

        if (this == obj) 
            return true;

        if (obj != null && obj instanceof AppUser) 
            return this.email.equals(((AppUser)(obj)).getEmail());

        return false;
    }
    
    
    public int calculateAge() {

        return (int) birthday.until(LocalDate.now(), ChronoUnit.YEARS);
    }
}