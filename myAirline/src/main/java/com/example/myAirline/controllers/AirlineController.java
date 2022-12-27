package com.example.myAirline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirline.models.Airline;
import com.example.myAirline.repositories.AirlineRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/airline")
    @Api(tags = {"Airline endpoint"})
    @Tag(name = "Airline endpoint")
public class AirlineController {
    
    @Autowired
    private AirlineRepository airlineRepository;


    @PostMapping("/saveAirline")
    @ApiOperation(value = "Save Airline object to db.")
    @ApiResponses(value = {
        // @ApiResponse()
    })
    public Airline saveAirline(@RequestBody Airline airline) {

        return airlineRepository.save(airline);
    }
}