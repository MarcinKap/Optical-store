package com.opticalstore.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;


//CONTROLLER

@Controller
public class RegisterController {

    private CustomUserService customUserService;

    public RegisterController(CustomUserService customUserService)
    {
        this.customUserService = customUserService;
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute LoginUser loginUser) {
      //  Optional<UserApp> userApp = customUserService.findUser(loginUser.getUsername());

        customUserService.saveUserApp(loginUser);
            return "redirect:/login";
        }

    @GetMapping("/register")
    public String loginPage() {
        return "register";
    }

}
