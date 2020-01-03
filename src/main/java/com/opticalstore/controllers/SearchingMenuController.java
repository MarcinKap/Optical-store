package com.opticalstore.controllers;


import com.opticalstore.commons.mappers.FormMapper;
import com.opticalstore.commons.mappers.GlassesMapper;
import com.opticalstore.commons.mappers.MarksMapper;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.services.FormService;
import com.opticalstore.services.GlassesService;
import com.opticalstore.services.MarksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class SearchingMenuController {
    private GlassesService glassesService;
    private GlassesMapper glassesMapper;
    private FormService formService;
    private FormMapper formMapper;
    private MarksMapper marksMapper;
    private MarksService marksService;



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


}
