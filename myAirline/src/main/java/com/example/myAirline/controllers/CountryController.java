package com.example.myAirline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirline.models.Country;
import com.example.myAirline.services.CountryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/country")
@Api(tags = {"Country endpoint"})
@Tag(name = "Country endpoint")
public class CountryController {
    
    @Autowired
    private CountryService countryService;


    @PostMapping("/save")
    @ApiOperation(value = "Save Country object to db.")
    @ApiResponses(value = {
        // @ApiResponse()
    })
    public Country save(@RequestBody Country country) {
        
        return countryService.save(country);
    }
}