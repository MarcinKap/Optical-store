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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
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

    //            (@RequestParam(value = "glassesType") String glassesType,
//                                        @RequestParam(value = "glassesGender") String glassesGender,
//                                        @RequestParam(value = "form") String form,
//                                        @RequestParam(value = "price") double price,
//                                        @RequestParam(value = "polarization") boolean polarization,
//                                        @RequestParam(value = "widthOfTheLens") int widthOfTheLens,
//                                        @RequestParam(value = "glassesMarks") String glassesMarks,
//                                        Model model)

//                glassesType,
//                glassesGender,
//                form,
//                price,
//                polarization,
//                widthOfTheLens,
//                glassesMarks);
//        model.addAttribute("glasses", glassesService.getGlassesByParam(glassesNumber123));
    @GetMapping("/advanced-search")
    public String advancedSearchGlasses(@ModelAttribute GlassesDto glassesDto, Model model) {

//        glassesService.getGlassesByParam(
//                glassesMapper.reverseMap(glassesDto).getGlassesType(),
//                glassesMapper.reverseMap(glassesDto).getGlassesGender(),
//                glassesMapper.reverseMap(glassesDto).getForm(),
//                glassesMapper.reverseMap(glassesDto).getPrice(),
//                glassesMapper.reverseMap(glassesDto).isPolarization(),
//                glassesMapper.reverseMap(glassesDto).getWidthOfTheLens(),
//                glassesMapper.reverseMap(glassesDto).getGlassesMarks());

        model.addAttribute("glasses", glassesService.getGlassesByParam(glassesMapper.reverseMap(glassesDto).getGlassesType(),
                glassesMapper.reverseMap(glassesDto).getGlassesGender(),
                glassesMapper.reverseMap(glassesDto).getForm(),
                glassesMapper.reverseMap(glassesDto).getPrice(),
                glassesMapper.reverseMap(glassesDto).getPolarization(),
                glassesMapper.reverseMap(glassesDto).getWidthOfTheLens(),
                glassesMapper.reverseMap(glassesDto).getGlassesMarks()));
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "find-glasses-by-param";
    }


    @GetMapping("/delete")
    public String deleteGlasses(@RequestParam(value = "glasses") int glassesNumber) {
        glassesService.deleteGlassesByNumber(glassesNumber);
        return "redirect:/";
    }

    @GetMapping("/deletemark")
    public String deleteMarks(@RequestParam(value = "marks") String markName) {
        marksService.deleteMarksByName(markName);
        return "redirect:/add-marks";
    }

    @GetMapping("/deleteform")
    public String deleteForms(@RequestParam(value = "forms") String formName) {
        formService.deleteFormsByName(formName);
        return "redirect:/add-forms";
    }

    @PostMapping("/add")
    public String addGlasses(@ModelAttribute GlassesDto glassesDto) {
        System.out.println(glassesDto);
        glassesService.saveGlasses(glassesMapper.reverseMap(glassesDto));
        return "redirect:/";
    }

    @PostMapping("/update")
    public String updateGlasses(@ModelAttribute GlassesDto glassesDto) {
        System.out.println(glassesDto);

        System.out.println(glassesDto.getGlassesNumber());
        glassesService.updateGlasses(glassesDto.getGlassesNumber(), glassesMapper.reverseMap(glassesDto));
        return "redirect:/";
    }

    @PostMapping("/addmarks")
    public String addMarks(@ModelAttribute GlassesMarkDto glassesMarkDto) {
        System.out.println(glassesMarkDto);
        marksService.saveGlassesMark(marksMapper.reverseMap(glassesMarkDto));

        return "redirect:/add-marks";
    }

    @PostMapping("/addforms")
    public String addForms(@ModelAttribute FormDto formDto) {
        System.out.println(formDto);
        formService.saveForm(formMapper.reverseMap(formDto));

        return "redirect:/add-forms";
    }


}



