package com.opticalstore.controllers;


import com.opticalstore.mappers.*;
import com.opticalstore.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

//
//@NoArgsConstructor
@AllArgsConstructor
@Controller
public class HomeController {

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

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(value = "searchingBy") Optional<String> searchingBy, @RequestParam(value = "ascending") Optional<Boolean> getAscending) {


        if (!searchingBy.isPresent()) {
            model.addAttribute("glasses", glassesService.getGlassesDto());
        } else {
            if (!getAscending.isPresent()) {
                getAscending = Optional.of(true);
            }
            model.addAttribute("glasses", glassesService.sortGlassesbyParam(searchingBy.get(), getAscending.get()));
            model.addAttribute("ascending", !getAscending.get());
        }
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "index";
    }
}