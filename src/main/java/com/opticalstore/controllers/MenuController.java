package com.opticalstore.controllers;


import com.opticalstore.mappers.FormMapper;
import com.opticalstore.mappers.GlassesMapper;
import com.opticalstore.mappers.MarksMapper;
import com.opticalstore.services.FormService;
import com.opticalstore.services.GlassesService;
import com.opticalstore.services.MarksService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class MenuController {
    private GlassesService glassesService;
    private FormService formService;
    private MarksService marksService;

    @GetMapping("/file/xls")
    public String getFileXls() throws NoSuchMethodException, IOException, IllegalAccessException, InvocationTargetException {
        glassesService.getFile("glasses");
        return "redirect:/";
    }


}
