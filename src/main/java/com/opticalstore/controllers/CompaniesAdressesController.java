package com.opticalstore.controllers;

import com.opticalstore.mappers.*;
import com.opticalstore.models.CompaniesAdressesDto;
import com.opticalstore.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class CompaniesAdressesController {


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



    @GetMapping("/delete-companies-adresses")
    public String deleteCompaniesAdresses(@RequestParam(value = "adressId") Long adressId, Model model) {
        companiesAdressesService.deleteCompaniesAdressesById(adressId);
        return "redirect:/account-invoice-data";
    }

    @PostMapping("/add-companies-adresses-form")
    public String addCompaniesAdressesForm(@ModelAttribute CompaniesAdressesDto companiesAdressesDto) {
        companiesAdressesService.saveCompaniesAdresses(companiesAdressesMapper.reverseMap(companiesAdressesDto));
        return "redirect:/account-invoice-data";
    }

    @PostMapping("/update-companies-adresses")
    public String updateCompaniesAdresses(@ModelAttribute CompaniesAdressesDto companiesAdressesDto) {
        companiesAdressesService.updateCompaniesAdresses(companiesAdressesDto.getId(), companiesAdressesMapper.reverseMap(companiesAdressesDto));
        return "redirect:/account-invoice-data";
    }

    @GetMapping("/form-update-companies-adresses")
    public String updateCompaniesAdresses(@RequestParam(value = "adressId") Long id, Model model) {
        model.addAttribute("companies_adresses", companiesAdressesService.getCompaniesAdressesById(id));
        model.addAttribute("glasses", glassesService.getGlassesDto());
        return "update-companies-adresses";
    }

    @GetMapping("/add-companies-adresses")
    public String addCompaniesAdressesPage(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        return "add-companies-adresses";
    }

}
