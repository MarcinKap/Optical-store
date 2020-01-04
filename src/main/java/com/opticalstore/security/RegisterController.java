package com.opticalstore.security;

import com.opticalstore.services.FormService;
import com.opticalstore.services.MarksService;
import com.opticalstore.validators.UserRegisterValidator;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Locale;


//CONTROLLER

@Controller
public class RegisterController {

    private CustomUserService customUserService;
    private MarksService marksService;
    private FormService formService;
    private MessageSource messageSource;

    public RegisterController(CustomUserService customUserService, MarksService marksService, FormService formService, MessageSource messageSource) {
        this.customUserService = customUserService;
        this.marksService = marksService;
        this.formService = formService;
        this.messageSource = messageSource;
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute LoginUser loginUser, BindingResult result, Model model, Locale locale) {
        UserApp userExist = customUserService.findUserByEmail(loginUser.getEmail());

        new UserRegisterValidator().validate(loginUser, result);
        new UserRegisterValidator().validateEmailExist(userExist, result);

        if (result.hasErrors()) {
            return "register";
        } else {
            customUserService.saveUserApp(loginUser);
            model.addAttribute("message", messageSource.getMessage("user.register.success", null, locale));
            model.addAttribute("loginUser", new LoginUser());
            return "login";
        }
    }

    @GetMapping("/register")
    public String loginPage(Model model) {
        LoginUser loginUser = new LoginUser();

        model.addAttribute("loginUser", loginUser);
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        return "register";
    }

}
