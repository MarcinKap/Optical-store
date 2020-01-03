package com.opticalstore.controllers;


import com.opticalstore.commons.mappers.*;
import com.opticalstore.models.*;
import com.opticalstore.services.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
//
//@NoArgsConstructor
//@AllArgsConstructor
@Controller
public class  HomeController {


    private GlassesService glassesService;
    private GlassesMapper glassesMapper;
    private FormService formService;
    private FormMapper formMapper;
    private MarksMapper marksMapper;
    private MarksService marksService;
    public AdressesMapper adressesMapper;
    public AdressesService adressesService;
    public CountriesService countriesService;
    public CountriesMapper countriesMapper;

    public CompaniesAdressesService companiesAdressesService;
    public CompaniesAdressesMapper companiesAdressesMapper;
//
    public HomeController(GlassesService glassesService, GlassesMapper glassesMapper, FormService formService, FormMapper formMapper, MarksMapper marksMapper, MarksService marksService, AdressesMapper adressesMapper, AdressesService adressesService, CountriesService countriesService, CountriesMapper countriesMapper, CompaniesAdressesService companiesAdressesService, CompaniesAdressesMapper companiesAdressesMapper) {
        this.glassesService = glassesService;
        this.glassesMapper = glassesMapper;
        this.formService = formService;
        this.formMapper = formMapper;
        this.marksMapper = marksMapper;
        this.marksService = marksService;
        this.adressesMapper = adressesMapper;
        this.adressesService = adressesService;
        this.countriesService = countriesService;
        this.countriesMapper = countriesMapper;
        this.companiesAdressesService = companiesAdressesService;
        this.companiesAdressesMapper = companiesAdressesMapper;
    }

    //    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'GUEST')")
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "index";
    }
}



// ^[a-zA-Z0-9]+(//._a-zA-Z0-9]*@[a-zA-Z0-9]+\\.[a-zA-Z]{2,}[\\.a-zA-Z]*?