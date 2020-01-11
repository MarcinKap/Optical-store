package com.opticalstore.controllers;

import com.opticalstore.mappers.FormMapper;
import com.opticalstore.mappers.GlassesMapper;
import com.opticalstore.models.FormDto;
import com.opticalstore.services.FormService;
import com.opticalstore.services.GlassesService;
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
public class FormsController {

    private FormService formService;
    private FormMapper formMapper;
    private GlassesService glassesService;
    private GlassesMapper glassesMapper;



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
        return "entieties/add-forms";
    }
}
