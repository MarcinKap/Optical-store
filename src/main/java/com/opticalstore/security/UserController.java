package com.opticalstore.security;


import com.opticalstore.validators.UserRegisterValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

@Controller
@AllArgsConstructor
public class UserController {

    private UserAppRepository userAppRepository;
    private CustomUserService customUserService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/signinoptions")
    public String changePasswordPage(Model model) {
        return "password-change";
    }

    @PostMapping("/password-change")
    public String changePassword(@RequestParam(value = "newPassword") String newPassword,@RequestParam(value = "newPasswordConfirmation") String newPasswordConfirmation,@RequestParam(value = "oldPassword") String oldPassword) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());

        if (passwordEncoder.matches(oldPassword, currentUser.getPassword())==true && newPassword.equals(newPasswordConfirmation)){
                customUserService.updateUserAppPassword(newPassword);
        }
        return "redirect:/";
    }

}
