package com.opticalstore.controllers;

import com.opticalstore.commons.mappers.GlassesMapper;
import com.opticalstore.commons.mappers.MarksMapper;
import com.opticalstore.models.GlassesMarkDto;
import com.opticalstore.services.GlassesService;
import com.opticalstore.services.MarksService;
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
public class MarksController {

    private GlassesService glassesService;
    private GlassesMapper glassesMapper;
    private MarksMapper marksMapper;
    private MarksService marksService;


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
}
