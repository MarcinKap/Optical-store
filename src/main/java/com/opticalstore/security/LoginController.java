package com.opticalstore.security;

import com.opticalstore.services.FormService;
import com.opticalstore.services.MarksService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class LoginController {
    private MarksService marksService;
    private FormService formService;

    public LoginController(MarksService marksService, FormService formService) {
        this.marksService = marksService;
        this.formService = formService;
    }
    @GetMapping("/login")
    public String loginPage(Model model)
    {
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "login";
    }
}
