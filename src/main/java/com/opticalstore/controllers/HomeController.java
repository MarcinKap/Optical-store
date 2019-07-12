package com.opticalstore.controllers;


import com.opticalstore.commons.mappers.GlassesMapper;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.services.GlassesService;
import com.opticalstore.services.MarksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private GlassesService glassesService;
    private GlassesMapper glassesMapper;
    private MarksService marksService;


    public HomeController(GlassesService glassesService, GlassesMapper glassesMapper, MarksService marksService) {
        this.glassesService = glassesService;
        this.glassesMapper = glassesMapper;
        this.marksService = marksService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        return "index";
    }


    @GetMapping("/add-page")
    public String addPage(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        return "add-planet";
    }

    @GetMapping("/delete")
    public String deleteGlasses(@RequestParam(value = "glasses") int glassesNumber) {
        glassesService.deleteGlassesByNumber(glassesNumber);
        return "redirect:/";
    }

    @PostMapping("/add")
    public String addGlasses(@ModelAttribute GlassesDto glassesDto) {
        System.out.println(glassesDto);
        glassesService.saveGlasses(glassesMapper.reverseMap(glassesDto));

        return "redirect:/";
    }


}



