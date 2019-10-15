package com.opticalstore.controllers;


import com.opticalstore.commons.mappers.AdressesMapper;
import com.opticalstore.commons.mappers.FormMapper;
import com.opticalstore.commons.mappers.GlassesMapper;
import com.opticalstore.commons.mappers.MarksMapper;
import com.opticalstore.models.*;
import com.opticalstore.security.CustomUserService;
import com.opticalstore.security.UserAppRepository;
import com.opticalstore.services.AdressesService;
import com.opticalstore.services.FormService;
import com.opticalstore.services.GlassesService;
import com.opticalstore.services.MarksService;
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

    private CustomUserService customUserService;
    private UserAppRepository userAppRepository;

//    public HomeController(GlassesService glassesService, GlassesMapper glassesMapper, MarksMapper marksMapper, FormMapper formMapper, MarksService marksService, FormService formService) {
//        this.glassesService = glassesService;
//        this.glassesMapper = glassesMapper;
//        this.marksMapper = marksMapper;
//        this.formMapper = formMapper;
//        this.marksService = marksService;
//        this.formService = formService;
//
//
//
//
//    }


    public HomeController(GlassesService glassesService, GlassesMapper glassesMapper, FormService formService, FormMapper formMapper, MarksMapper marksMapper, MarksService marksService, AdressesMapper adressesMapper, AdressesService adressesService) {
        this.glassesService = glassesService;
        this.glassesMapper = glassesMapper;
        this.formService = formService;
        this.formMapper = formMapper;
        this.marksMapper = marksMapper;
        this.marksService = marksService;
        this.adressesMapper = adressesMapper;
        this.adressesService = adressesService;
    }

    //    @PreAuthorize("hasAnyRole('ADMIN', 'USER', 'GUEST')")
    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "index";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-page")
    public String addPage(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        return "add-glasses";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-marks")
    public String addMark(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        return "add-marks";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-forms")
    public String addForm(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("forms", formService.getFormDto());
        return "add-forms";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update-glasses")
    public String updateGlasses(@RequestParam(value = "glassesNumber") int glassesNumber, Model model) {
        model.addAttribute("glasses", glassesService.getGlassesByNumber(glassesNumber));
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "update-glasses";
    }

    @GetMapping("/search")
    public String searchGlasses(@RequestParam(value = "glassesNumber") int glassesNumber, Model model) {

        glassesService.getGlassesByNumber(glassesNumber);

        model.addAttribute("glasses", glassesService.getGlassesByNumber(glassesNumber));
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "find-glasses-by-param";
    }

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

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String deleteGlasses(@RequestParam(value = "glasses") int glassesNumber) {
        glassesService.deleteGlassesByNumber(glassesNumber);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deletemark")
    public String deleteMarks(@RequestParam(value = "marks") String markName) {
        marksService.deleteMarksByName(markName);
        return "redirect:/add-marks";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/deleteform")
    public String deleteForms(@RequestParam(value = "forms") String formName) {
        formService.deleteFormsByName(formName);
        return "redirect:/add-forms";
    }


    @GetMapping("/accountindex")
    public String accountIndex(Model model) {

        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        return "account-index";
    }

    @GetMapping("/account-adresses")
    public String accountAdresses(Model model) {

        model.addAttribute("adresses", adressesService.getAdressesDto());
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        return "account-adresses";
    }

    @GetMapping("/account-invoice-data")
    public String accountInvoiceData(Model model) {

//        model.addAttribute("glasses", glassesService.getGlassesDto());
//        model.addAttribute("marks", marksService.getGlassesMarkDto());
//        model.addAttribute("forms", formService.getFormDto());

        return "account-invoice-data";
    }

    @GetMapping("/account-data")
    public String accountData(Model model) {

        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        return "account-data";
    }

    //    @PreAuthorize("hasAnyRole")
    @GetMapping("/add-adress")
    public String addAdress(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        return "add-adress";
    }


//    @PreAuthorize("hasAnyRole")
//    @PostMapping("/add-adresses")
//    public String addAdresses(@ModelAttribute GlassesDto glassesDto) {
//        glassesService.saveGlasses(glassesMapper.reverseMap(glassesDto));
//        return "redirect:/";
//    }


    @PostMapping("/addadresses")
    public String addAdressesByUser(@ModelAttribute AdressesDto adressesDto) {


        adressesService.saveAdresses(adressesMapper.reverseMap(adressesDto));
        return "redirect:/";
    }


    @PostMapping("/add")
    public String addGlasses(@ModelAttribute GlassesDto glassesDto) {
        glassesService.saveGlasses(glassesMapper.reverseMap(glassesDto));
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public String updateGlasses(@ModelAttribute GlassesDto glassesDto) {
        glassesService.updateGlasses(glassesDto.getGlassesNumber(), glassesMapper.reverseMap(glassesDto));
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addmarks")
    public String addMarks(@ModelAttribute GlassesMarkDto glassesMarkDto) {
        System.out.println(glassesMarkDto);
        marksService.saveGlassesMark(marksMapper.reverseMap(glassesMarkDto));

        return "redirect:/add-marks";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addforms")
    public String addForms(@ModelAttribute FormDto formDto) {
        formService.saveForm(formMapper.reverseMap(formDto));

        return "redirect:/add-forms";
    }


    @GetMapping("/file/xls")
    public String getFileXls() throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        glassesService.getFile("glasses");
        return "redirect:/";
    }


}



