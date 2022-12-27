package com.example.myAirline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirline.models.Plane;
import com.example.myAirline.services.PlaneService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/plane")
@Api(tags = {"Plane endpoint"})
@Tag(name = "Plane endpoint")
public class PlaneController {

    @Autowired
    private PlaneService planeService;


    @PostMapping("/save")
    @ApiOperation(value = "Save Plane object to db.")
    @ApiResponses(value = {
        // @ApiResponse()
    })
    public Plane save(@RequestBody Plane plane) {

        return planeService.save(plane);
    }
}