package com.opticalstore.security;

import com.opticalstore.services.FormService;
import com.opticalstore.services.MarksService;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

@Controller
public class LoginController {
    private MarksService marksService;
    private FormService formService;
    private MessageSource messageSource;

    public LoginController(MarksService marksService, FormService formService, MessageSource messageSource) {
        this.marksService = marksService;
        this.formService = formService;
        this.messageSource = messageSource;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "security/login";
    }

    @RequestMapping("/login-error.html")
    public String loginError(Model model, Locale locale) {

//        model.addAttribute("loginError", true);
        model.addAttribute("loginErrorMessage", messageSource.getMessage("error.wrongLogin", null, locale));

        return "security/login";
    }


}
