package com.example.myAirline.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;


/**
 * Thymeleaf Controller for pages that don't need much back end.
 * 
 * @since 1.0
 * @author Florin Schikarski
 */
@Controller
@Api(tags = {"Default endpoint"})
@Tag(name = "Default endpoint")
public class DefaultController {
    
    @GetMapping("/")
    @ApiOperation(value = "Redirect url root to index.html.")
    public String getIndexPage() {

        return "index";
    }
}