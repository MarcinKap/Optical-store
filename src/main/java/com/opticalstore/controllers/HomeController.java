package com.opticalstore.controllers;


import com.opticalstore.commons.mappers.*;
import com.opticalstore.models.*;
import com.opticalstore.services.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

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

    //////////////////
    //SEARCHING MENU//
    //////////////////
    @GetMapping("/advanced-search")
    public String advancedSearchGlasses(
            @ModelAttribute GlassesDto glassesDto,
            @RequestParam(value = "priceLowerLimit") double priceLowerLimit,
            @RequestParam(value = "priceUpperLimit") double priceUpperLimit,
            @RequestParam(value = "widthOfTheLensLowerLimit") int widthOfTheLensLowerLimit,
            @RequestParam(value = "widthOfTheLensUpperLimit") int widthOfTheLensUpperLimit,
            Model model) {
        model.addAttribute("glasses",
                glassesService.getGlassesByParam(
                        glassesMapper.reverseMap(glassesDto).getGlassesType(),
                        glassesMapper.reverseMap(glassesDto).getGlassesGender(),
                        glassesMapper.reverseMap(glassesDto).getForm(),
                        priceLowerLimit,
                        priceUpperLimit,
                        glassesMapper.reverseMap(glassesDto).getPolarization(),
                        widthOfTheLensLowerLimit,
                        widthOfTheLensUpperLimit,
                        glassesMapper.reverseMap(glassesDto).getGlassesMarks()
                ));

        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "find-glasses-by-param";
    }
    @GetMapping("/search")
    public String searchGlasses(@RequestParam(value = "glassesNumber") int glassesNumber, Model model) {
        model.addAttribute("glasses", glassesService.getGlassesByNumber(glassesNumber));
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "find-glasses-by-param";
    }



    ///////////
    //GLASSES//
    ///////////
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-page")
    public String addPage(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "add-glasses";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public String updateGlasses(@ModelAttribute GlassesDto glassesDto) {
        glassesService.updateGlasses(glassesDto.getGlassesNumber(), glassesMapper.reverseMap(glassesDto));
        return "redirect:/";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String deleteGlasses(@RequestParam(value = "glasses") int glassesNumber) {
        glassesService.deleteGlassesByNumber(glassesNumber);
        return "redirect:/";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update-glasses")
    public String updateGlasses(@RequestParam(value = "glassesNumber") int glassesNumber, Model model) {
        model.addAttribute("glasses", glassesService.getGlassesByNumber(glassesNumber));
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        return "update-glasses";
    }
    @PostMapping("/add")
    public String addGlasses(@ModelAttribute GlassesDto glassesDto) {
        glassesService.saveGlasses(glassesMapper.reverseMap(glassesDto));
        return "redirect:/";
    }


    ///////////////////
    //SUNGLASSES LIST//
    ///////////////////
    @GetMapping("/sunglasses")
    public String sunglasses(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesByType("Przeciwsłoneczne"));
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "index";
    }

    /////////////////////////////
    //PRESCRIPTION GLASSES LIST//
    /////////////////////////////
    @GetMapping("/prescription-glasses")
    public String prescriptionGlasses(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesByType("Korekcyjne"));
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "index";
    }

    /////////////////
    //MARKS - MARKI//
    /////////////////
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addmarks")
    public String addMarks(@ModelAttribute GlassesMarkDto glassesMarkDto) {
        marksService.saveGlassesMark(marksMapper.reverseMap(glassesMarkDto));

        return "redirect:/add-marks";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deletemark")
    public String deleteMarks(@RequestParam(value = "marks") String markName) {
        marksService.deleteMarksByName(markName);
        return "redirect:/add-marks";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-marks")
    public String addMark(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        return "add-marks";
    }


    ////////////////////
    //FORMS - KSZTALTY//
    ////////////////////
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addforms")
    public String addForms(@ModelAttribute FormDto formDto) {
        formService.saveForm(formMapper.reverseMap(formDto));

        return "redirect:/add-forms";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteform")
    public String deleteForms(@RequestParam(value = "forms") String formName) {
        formService.deleteFormsByName(formName);
        return "redirect:/add-forms";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-forms")
    public String addForm(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("forms", formService.getFormDto());
        return "add-forms";
    }

    //////////////////////
    //ADRESY UŻYTKOWNIKA//
    //////////////////////
    @GetMapping("/account-adresses")
    public String accountAdresses(Model model) {
        model.addAttribute("adresses", adressesService.getAdressesDto());
        return "account-adresses";
    }
    @PostMapping("/addadresses")
    public String addAdressesByUser(@ModelAttribute AdressesDto adressesDto) {
        adressesService.saveAdresses(adressesMapper.reverseMap(adressesDto));
        return "redirect:/account-adresses";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteadress")
    public String deleteAdresses(@RequestParam(value = "adressId") Long id) {
        adressesService.deleteAdressesById(id);
        return "redirect:/account-adresses";
    }
    @PostMapping("/updateadresses")
    public String updateAdresses(@ModelAttribute AdressesDto adressesDto) {
        adressesService.updateAdresses(adressesDto.getId(), adressesMapper.reverseMap(adressesDto));
        return "redirect:/account-adresses";
    }
    @GetMapping("/add-adress")
    public String addAdress(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        model.addAttribute("countries", countriesService.getCountriesDto());
        return "add-adress";
    }

    @GetMapping("/update-adresses")
    public String updateAdresses(@RequestParam(value = "adressId") Long adressId, Model model) {
        model.addAttribute("adresses", adressesService.getAdressesById(adressId));
        model.addAttribute("countries", countriesService.getCountriesDto());
        return "update-adress";
    }




    //////////////////////
    //COMPANIES ADRESSES//
    //////////////////////
    @GetMapping("/account-invoice-data")
    public String accountInvoiceData(Model model) {
        model.addAttribute("companies_adresses", companiesAdressesService.getCompaniesAdressesDto());
        model.addAttribute("glasses", glassesService.getGlassesDto());
        return "account-invoice-data";
    }

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
        System.out.println(companiesAdressesDto);
        return "redirect:/account-invoice-data";
    }

    @GetMapping("/form-update-companies-adresses")
    public String updateCompaniesAdresses(@RequestParam(value = "adressId") Long id, Model model) {
        model.addAttribute("companies_adresses", companiesAdressesService.getCompaniesAdressesById(id));
        System.out.println(id);
        System.out.println(companiesAdressesService.getCompaniesAdressesById(id));
        model.addAttribute("glasses", glassesService.getGlassesDto());
        return "update-companies-adresses";
    }

    @GetMapping("/add-companies-adresses")
    public String addCompaniesAdressesPage(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        return "add-companies-adresses";
    }



    ///////////////////
    //MOJE ZAMOWIENIA//
    ///////////////////
    @GetMapping("/accountindex")
    public String accountIndex(Model model) {

        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        return "account-index";
    }


    /////////////
    //MOJE DANE//
    /////////////
    @GetMapping("/account-data")
    public String accountData(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "account-data";
    }


    @GetMapping("/file/xls")
    public String getFileXls() throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        glassesService.getFile("glasses");
        return "redirect:/";
    }





}



