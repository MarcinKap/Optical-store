package com.opticalstore.controllers;


import com.opticalstore.mappers.*;
import com.opticalstore.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

//
//@NoArgsConstructor
@AllArgsConstructor
@Controller
public class HomeController {

    private HttpServletRequest httpServletRequest;
    private GlassesService glassesService;
    private FormService formService;
    private MarksService marksService;
    public AdressesMapper adressesMapper;
    public AdressesService adressesService;
    public CountriesService countriesService;
    public CountriesMapper countriesMapper;
    public CompaniesAdressesService companiesAdressesService;
    public CompaniesAdressesMapper companiesAdressesMapper;

    @GetMapping("/")
    public String homePage(Model model, @RequestParam(value = "orderBy") Optional<String> orderBy, @RequestParam(value = "ascOrDesc") Optional<Boolean> ascOrDesc, @RequestParam(value = "searchingByParameter") Optional<String> searchingByParameter, @RequestParam(value = "searchingByValue") Optional<String> searchingByValue) {
        if (!ascOrDesc.isPresent()) {
            ascOrDesc = Optional.of(true);
        }
            model.addAttribute("glasses", glassesService.getGlassesWithParams(orderBy, ascOrDesc, searchingByParameter, searchingByValue));
            model.addAttribute("ascOrDesc", !ascOrDesc.get());


        if(searchingByParameter.isPresent()){
            model.addAttribute("searchingByParameter", searchingByParameter.get());
        }
        if (searchingByValue.isPresent()){
            model.addAttribute("searchingByValue", searchingByValue.get());
        }

        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "index";
    }
}