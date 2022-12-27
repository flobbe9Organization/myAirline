package com.example.myAirline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.myAirline.models.AppUser;
import com.example.myAirline.services.AppUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Controller for all endpoints directly related to the AppUserService.
 * 
 * @see AppUserService
 * @since 1.0
 * @author Florin Schikarski
 */
@RestController
@RequestMapping("/appUser")
@Api(tags = {"AppUser endpoint"})
@Tag(name = "AppUser endpoint")
public class AppUserController {

    @Autowired
    private AppUserService appUserService;

    
    @PostMapping("/addNew")
    @ApiOperation(value = "Save AppUser to db.")
    @ApiResponses(value = {
        // @ApiResponse()
    })
    public AppUser addNew(@RequestBody AppUser appUser) {

        return appUserService.addNew(appUser);
    }


    @GetMapping("/getByUserName") 
    @ApiOperation(value = "Get AppUser by username.")
    @ApiResponses(value = {
        // @ApiResponse(code = )
    })
    public AppUser getByUserName(@RequestParam("userName") String userName) {
        
        return appUserService.getByEmail(userName);
    }


    @GetMapping("/confirmAccount/{token}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Confirmed")
    @ApiOperation(value = "Enable AppUser.")
    @ApiResponses(value = {
        // @ApiResponse()
    })
    public void confirmAccount(@PathVariable String token) {

        appUserService.confirmAccount(token);
    }
}