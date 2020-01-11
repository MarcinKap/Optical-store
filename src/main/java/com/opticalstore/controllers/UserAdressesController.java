package com.opticalstore.controllers;

import com.opticalstore.mappers.*;
import com.opticalstore.models.Adresses;
import com.opticalstore.models.AdressesDto;
import com.opticalstore.services.*;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserAdressesController {

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


    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/add-adress")
    public String addAdressesByUser(@ModelAttribute AdressesDto adressesDto) {
        adressesService.saveAdresses(adressesMapper.reverseMap(adressesDto));
        return "redirect:/account-menu/account-adresses";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/deleteadress")
    public String deleteAdresses(@RequestParam(value = "adressId") Long id) {
        adressesService.deleteAdressesById(id);
        return "redirect:/account-menu/account-adresses";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping("/update-adress")
    public String updateAdresses(@ModelAttribute AdressesDto adressesDto) {
        adressesService.updateAdresses(adressesDto.getId(), adressesMapper.reverseMap(adressesDto));
        return "redirect:/account-menu/account-adresses";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("account-menu/account-adresses/add-adress")
    public String addAdress(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        model.addAttribute("countries", countriesService.getCountriesDto());

        String action = "add-adress";
        model.addAttribute("action", action);
        model.addAttribute("adresses", new Adresses());
        return "entieties/add-adress";
    }
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("account-menu/account-adresses/update-adresses")
    public String updateAdresses(@RequestParam(value = "adressId") Long adressId, Model model) {
//        model.addAttribute("adresses", adressesService.getAdressesById(adressId));
        model.addAttribute("countries", countriesService.getCountriesDto());

        String action = "update-adress";

        model.addAttribute("action", action);
        model.addAttribute("adresses", adressesService.getAdressesById(adressId));
        return "entieties/add-adress";
    }
}
