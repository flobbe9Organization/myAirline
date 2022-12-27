package com.example.myAirline.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.myAirline.enums.TravelClass;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Class defining a row of seats on a plane.
 * 
 * @since 1.0
 * @author Florin Schikasrki
 */
@Entity
@Table(name = "plane_row")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Row {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @OneToMany(fetch = FetchType.LAZY, 
               cascade = CascadeType.ALL, 
               mappedBy = "row")
    @JsonManagedReference
    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private List<Seat> seats;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TravelClass travelClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plane_id", nullable = false)
    @JsonBackReference
    private Plane plane;


    @Override
    public String toString() {

        return "Row " + this.number + " in " + this.plane;
    }
}