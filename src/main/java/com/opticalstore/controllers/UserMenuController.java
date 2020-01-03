package com.opticalstore.controllers;


import com.opticalstore.commons.mappers.*;
import com.opticalstore.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserMenuController {

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

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/accountindex")
    public String accountIndex(Model model) {

        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        return "account-index";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-adresses")
    public String accountAdresses(Model model) {
        model.addAttribute("adresses", adressesService.getAdressesDto());
        return "account-adresses";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-invoice-data")
    public String accountInvoiceData(Model model) {



        model.addAttribute("companies_adresses", companiesAdressesService.getCompaniesAdressesDto());
        model.addAttribute("glasses", glassesService.getGlassesDto());
        return "account-invoice-data";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-data")
    public String accountData(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "account-data";
    }





}
