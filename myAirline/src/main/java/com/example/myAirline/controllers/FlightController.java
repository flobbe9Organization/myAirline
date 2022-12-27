package com.example.myAirline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirline.models.Flight;
import com.example.myAirline.services.FlightService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/flight")
@Api(tags = {"Flight endpoint"})
@Tag(name = "Flight endpoint")
public class FlightController {
    
    @Autowired
    private FlightService flightService;

    
    @PostMapping("/save")
    @ApiOperation(value = "Save Flight object to db.")
    @ApiResponses(value = {
        // @ApiResponse()
    })
    public Flight save(@RequestBody Flight flight) {
        
        return flightService.save(flight);
    }
}