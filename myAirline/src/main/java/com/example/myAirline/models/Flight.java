package com.example.myAirline.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class defining a flight.
 * 
 * @since 1.0
 * @author Florin Schikasrki
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Flight {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @NotNull
    @Min(0) // TODO: number must be unique, maybe auto generate with hibernate...
    private Integer number;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "plane_id")
    @NotNull
    private Plane plane;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "airline_id")
    @NotNull
    private Airline airline;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "flights")
    @NotEmpty
    private List<Airport> airports;

    @OneToMany(fetch = FetchType.LAZY,
               cascade = CascadeType.ALL,
               mappedBy = "flight")
    // @JsonManagedReference
    @NotEmpty
    private Set<Booking> bookings;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @NotNull
    private LocalDateTime localDepartureTime;

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @NotNull
    private LocalDateTime localLandingTime;

    @NotNull
    @DecimalMin("0.0")
    private Double duration; /** In minutes */ // TODO: set this one by method, consider the time zones of the airports, 2 digits after comma

    @NotNull
    @Min(0)
    private Integer gate;

    
    @Override
    public String toString() {
        
        return "Flight " + this.number + " from " /* departureAirport name */ + " to " + /* destinationAirport name */ ".";
    }
}