package com.opticalstore.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//CONNTROLLER

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }




}
