package com.example.myAirline.controllers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

import com.example.myAirline.enums.AppUserRole;
import com.example.myAirline.models.AppUser;
import com.example.myAirline.services.AppUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AppUserController.class)
public class AppUserControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AppUserService appUserService;

    private AppUser appUser = new AppUser("florin.schikarski@gmail.com",
                                          "password",
                                          AppUserRole.USER,
                                          "Florin",
                                          "Schikarski", 
                                          LocalDate.of(2001, 02, 12));


    @Test
    public void testAddNew() throws Exception {

        when(appUserService.addNew(appUser))
            .thenReturn(appUser);

        Assertions.assertThrows(NestedServletException.class, () -> 

            mockMvc.perform(post("/appUser/addNew")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectToJson(appUser)))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn()
        );
    }


    @Test
    public void testGetByUserName() throws Exception {

        when(appUserService.getByEmail(appUser.getEmail()))
            .thenReturn(appUser);

        mockMvc.perform(get("/appUser/getByUserName?userName=florin.schikarski@gmail.com"))
            .andExpect(status().isOk())
            .andDo(print())
            .andReturn();
    }


////////


    private String objectToJson(Object obj) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper.writeValueAsString(obj);
    }
}