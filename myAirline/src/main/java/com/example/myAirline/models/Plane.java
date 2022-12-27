package com.example.myAirline.models;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.myAirline.enums.TravelClass;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class defining a Plane.
 * 
 * @since 1.0
 * @author Florin Schikasrki
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Plane {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false) // TODO: number must be unique inside one airline, maybe auto generate with hibernate...
    private Integer number;
    
    @Column(nullable = false)
    private Integer passengerCapacity;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.LAZY)
    @Column(nullable = false)
    private Set<TravelClass> travelClasses;
    
    @OneToMany(fetch = FetchType.LAZY, 
               cascade = CascadeType.ALL,
               mappedBy = "plane")
    @JsonManagedReference
    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private List<Row> rows;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "plane")
    private Flight flight;


    public Plane(Integer number, 
                 Set<TravelClass> travelClasses, 
                 Integer passengerCapacity,
                 List<Row> rows) {

        this.number = number;
        this.travelClasses = travelClasses;
        this.passengerCapacity = passengerCapacity;
        this.rows = rows;
    }


    @Override
    public String toString() {
        
        return "Plane " + this.number + " from.";
    }
}