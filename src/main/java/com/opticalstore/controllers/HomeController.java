package com.opticalstore.controllers;


import com.opticalstore.mappers.AdressesMapper;
import com.opticalstore.mappers.CompaniesAdressesMapper;
import com.opticalstore.mappers.CountriesMapper;
import com.opticalstore.models.GlassesSearchingForm;
import com.opticalstore.services.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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
    public String homePage(Model model, @ModelAttribute("glassesSearchingForm") Optional<GlassesSearchingForm> glassesSearchingForm) {
        Optional<Boolean> ascOrDesc = glassesService.ascOrDescSettings(glassesSearchingForm.get().getAscOrDesc());


        System.out.println(httpServletRequest.getRequestURI());

        model.addAttribute("ascOrDesc", ascOrDesc.get());
        model.addAttribute("glasses", glassesService.getGlassesWithParams(glassesSearchingForm, ascOrDesc));

        model.addAttribute("glassesSearchingForm", glassesSearchingForm.get());

//        if (httpServletRequest.getQueryString() != null) {
//            addingPresenceAttributesFromCurrentGlassesSearchingForm(model, glassesSearchingForm);
//        }


        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "index";
    }

    public void addingPresenceAttributesFromCurrentGlassesSearchingForm(Model model, Optional<GlassesSearchingForm> glassesSearchingForm) {

        if (glassesSearchingForm.get().getGlassesType() != null) {
            model.addAttribute("glassesType", glassesSearchingForm.get().getGlassesType().get());
        }
        if (glassesSearchingForm.get().getPolarization() != null) {
            model.addAttribute("polarization", glassesSearchingForm.get().getPolarization().get());
        }
        if (glassesSearchingForm.get().getGlassesMarks() != null) {
            model.addAttribute("glassesMarks", glassesSearchingForm.get().getGlassesMarks().get());
        }
        if (glassesSearchingForm.get().getGlassesNumber() != null) {
            model.addAttribute("glassesNumber", glassesSearchingForm.get().getGlassesNumber().get());
        }
        if (glassesSearchingForm.get().getGlassesGender() != null) {
            model.addAttribute("glassesGender", glassesSearchingForm.get().getGlassesGender().get());
        }
        if (glassesSearchingForm.get().getForm() != null) {
            model.addAttribute("form", glassesSearchingForm.get().getForm().get());
        }


    }

}