package com.opticalstore.controllers;


import com.opticalstore.mappers.AdressesMapper;
import com.opticalstore.mappers.CompaniesAdressesMapper;
import com.opticalstore.mappers.CountriesMapper;
import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesSearchingForm;
import com.opticalstore.services.*;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

//
//@NoArgsConstructor
@AllArgsConstructor
@Controller
public class HomeController {
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
    public String homePage(
            Model model,
            @ModelAttribute("glassesSearchingForm") Optional<GlassesSearchingForm> glassesSearchingForm,
            @Param("page") Optional<Integer> page,
            @Param("pageSize") Optional<Integer> pageSize) throws IOException {

//      Sending information for searching glasses
        Optional<Boolean> ascOrDesc = glassesService.ascOrDescSettings(glassesSearchingForm.get().getAscOrDesc());
        model.addAttribute("ascOrDesc", ascOrDesc.get());
        model.addAttribute("glassesSearchingForm", glassesSearchingForm.get());


//      Creating GlassesList
        System.out.println("Page size" + pageSize);

        Page<Glasses> glassesList = glassesService.getGlassesWithParamsPaginated(glassesSearchingForm, ascOrDesc, page, pageSize);
        if (glassesList.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, glassesList.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);   // stream page numbers
        }
        model.addAttribute("glasses", glassesList);

        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "index";
    }


}