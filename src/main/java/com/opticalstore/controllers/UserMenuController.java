package com.opticalstore.controllers;


import com.opticalstore.mappers.*;
import com.opticalstore.security.UserApp;
import com.opticalstore.security.UserAppRepository;
import com.opticalstore.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class UserMenuController {

    private GlassesService glassesService;
    private FormService formService;
    private MarksService marksService;
    public AdressesMapper adressesMapper;
    public AdressesService adressesService;
    public CountriesService countriesService;
    public CountriesMapper countriesMapper;

    private UserAppRepository userAppRepository;

    public CompaniesAdressesService companiesAdressesService;
    public CompaniesAdressesMapper companiesAdressesMapper;

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-index")
    public String accountIndex(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "account-menu/account-index";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-adresses")
    public String accountAdresses(Model model) {
        model.addAttribute("adresses", adressesService.getAdressesDto());
        return "account-menu/account-adresses";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-invoice-data")
    public String accountInvoiceData(Model model) {
        model.addAttribute("companies_adresses", companiesAdressesService.getCompaniesAdressesDto());
        model.addAttribute("glasses", glassesService.getGlassesDto());
        return "account-menu/account-invoice-data";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/account-menu/account-data")
    public String accountData(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());

        model.addAttribute("user", currentUser);

        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "account-menu/account-data";
    }





}
