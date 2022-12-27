package com.example.myAirline.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class defining a city.
 * 
 * @since 1.0
 * @author Florin Schikarski
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer zipCode;

    @Column(nullable = false)
    private Integer timeZone;

    @OneToMany(fetch = FetchType.LAZY,
               cascade = CascadeType.ALL)
    @JoinTable(name = "city_airports", 
               inverseJoinColumns = @JoinColumn(name = "airport_id"))
    @JsonManagedReference
    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private Set<Airport> airports;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    @JsonBackReference
    private Country country;


    public City(String name, 
                Integer zipCode, 
                Country country, 
                Set<Airport> airports,
                Integer timeZone) {

        this.name = name;
        this.zipCode = zipCode;
        this.country = country;
        // this.airports = airports;
        this.timeZone = timeZone;
    }


    @Override
    public String toString() {

        return this.name;
    }
}