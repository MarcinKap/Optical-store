package com.opticalstore.controllers;


import com.opticalstore.commons.mappers.FormMapper;
import com.opticalstore.commons.mappers.GlassesMapper;
import com.opticalstore.commons.mappers.MarksMapper;
import com.opticalstore.models.FormDto;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.models.GlassesMarkDto;
import com.opticalstore.services.FormService;
import com.opticalstore.services.GlassesService;
import com.opticalstore.services.MarksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    private GlassesService glassesService;
    private GlassesMapper glassesMapper;
    private MarksMapper marksMapper;
    private FormMapper formMapper;
    private MarksService marksService;
    private FormService formService;


    public HomeController(GlassesService glassesService, GlassesMapper glassesMapper, MarksMapper marksMapper, FormMapper formMapper, MarksService marksService, FormService formService) {
        this.glassesService = glassesService;
        this.glassesMapper = glassesMapper;
        this.marksMapper = marksMapper;
        this.formMapper = formMapper;
        this.marksService = marksService;
        this.formService = formService;
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
        model.addAttribute("forms", formService.getFormDto());
        return "add-glasses";
    }

    @GetMapping("/add-marks")
    public String addMark(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        return "add-marks";
    }
    @GetMapping("/add-forms")
    public String addForm(Model model) {
        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("forms", formService.getFormDto());
        return "add-forms";
    }

//    @GetMapping("/update-glasses")
//    public String updateGlasses(@RequestParam(value = "glasses") int glassesNumber, Model model) {
//        // todo do zrobienia update planet
//        model.addAttribute("glasses", glassesService.getGlassesDto());
//        return "add-forms";
//    }
//@RequestBody() GlassesDto glasses, Model model
    //todo dokonczyc update
@GetMapping("/update-glasses")
public String updateGlasses(Model model) {
    model.addAttribute("glasses", glassesService.getGlassesDto());
    model.addAttribute("forms", formService.getFormDto());
    return "update-glasses";
}
    @GetMapping("/search")
    public String searchGlasses(@RequestParam(value = "glassesNumber") int glassesNumber123, Model model) {

            glassesService.getGlassesByNumber(glassesNumber123);

            model.addAttribute("glasses", glassesService.getGlassesByNumber(glassesNumber123));
            model.addAttribute("marks", marksService.getGlassesMarkDto());
            model.addAttribute("forms", formService.getFormDto());
            return "find-glasses-by-param";
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
    @PostMapping("/addmarks")
    public String addMarks(@ModelAttribute GlassesMarkDto glassesMarkDto) {
        System.out.println(glassesMarkDto);
        marksService.saveGlassesMark(marksMapper.reverseMap(glassesMarkDto));

        return "redirect:/add-marks";
    }

    @GetMapping("/deletemark")
    public String deleteMarks(@RequestParam(value = "marks") String markName) {
        marksService.deleteMarksByName(markName);
        return "redirect:/add-marks";
    }

    @PostMapping("/addforms")
    public String addForms(@ModelAttribute FormDto formDto) {
        System.out.println(formDto);
        formService.saveForm(formMapper.reverseMap(formDto));

        return "redirect:/add-forms";
    }

    @GetMapping("/deleteform")
    public String deleteForms(@RequestParam(value = "forms") String formName) {
        formService.deleteFormsByName(formName);
        return "redirect:/add-forms";
    }


}



