package com.example.myAirline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirline.models.Airport;
import com.example.myAirline.services.AirportService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/airport")
@Api(tags = {"Airport endpoint"})
@Tag(name = "Airport endpoint")
public class AirportController {
    
    @Autowired
    private AirportService airportService;


    @PostMapping("/save")
    @ApiOperation(value = "Save Airport object to db.")
    @ApiResponses(value = {
        // @ApiResponse()
    })
    public Airport save(@RequestBody Airport airport) {

        return airportService.save(airport);
    }
}