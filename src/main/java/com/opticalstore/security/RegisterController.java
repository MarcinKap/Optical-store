package com.opticalstore.security;

import com.opticalstore.services.FormService;
import com.opticalstore.services.MarksService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


//CONTROLLER

@Controller
public class RegisterController {

    private CustomUserService customUserService;
    private MarksService marksService;
    private FormService formService;
    public RegisterController(CustomUserService customUserService, MarksService marksService, FormService formService) {
        this.customUserService = customUserService;
        this.marksService = marksService;
        this.formService = formService;
    }
    @PostMapping("/signup")
    public String registerUser(@ModelAttribute LoginUser loginUser) {

        customUserService.saveUserApp(loginUser);
            return "redirect:/login";
        }
    @GetMapping("/register")
    public String loginPage(Model model) {
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());
        return "register";
    }

}
