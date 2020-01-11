package com.opticalstore.controllers;


import com.opticalstore.commons.extras.CreatorXLS;
import com.opticalstore.mappers.FormMapper;
import com.opticalstore.mappers.GlassesMapper;
import com.opticalstore.mappers.MarksMapper;
import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.services.FormService;
import com.opticalstore.services.GlassesService;
import com.opticalstore.services.MarksService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

//@CrossOrigin
//@RestController
@Controller
@AllArgsConstructor
public class GlassesController {

    private GlassesService glassesService;
    private GlassesMapper glassesMapper;
    private FormService formService;
    private FormMapper formMapper;
    private MarksMapper marksMapper;
    private MarksService marksService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-glasses")
    public String addPage(Model model) {

        String action = "add";
        model.addAttribute("action", action);

        model.addAttribute("glasses", glassesService.getGlassesDto());
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());

        model.addAttribute("finded_glasses", new GlassesDto());

        return "entieties/add-glasses";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public String updateGlasses(@ModelAttribute GlassesDto glassesDto) {
        System.out.println(glassesDto);
        glassesService.updateGlasses(glassesDto.getGlassesNumber(), glassesMapper.reverseMap(glassesDto));
        return "redirect:/";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String deleteGlasses(@RequestParam(value = "glasses") int glassesNumber) {
        glassesService.deleteGlassesByNumber(glassesNumber);
        return "redirect:/";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/update-glasses")
    public String updateGlasses(@RequestParam(value = "glassesNumber") int glassesNumber, Model model) {
//        model.addAttribute("glasses", glassesService.getGlassesByNumber(glassesNumber));
        String action = "update";
        model.addAttribute("action", action);
        model.addAttribute("finded_glasses", glassesService.getGlassesByNumber(glassesNumber));
        model.addAttribute("marks", marksService.getGlassesMarkDto());
        model.addAttribute("forms", formService.getFormDto());


        return "entieties/add-glasses";

//        return "entieties/update-glasses";
    }
    @PostMapping("/add")
    public String addGlasses(@ModelAttribute GlassesDto glassesDto) {
        glassesService.saveGlasses(glassesMapper.reverseMap(glassesDto));
        return "redirect:/";
    }


    /////////////////





//    public GlassesController(GlassesService glassesService) {
//        this.glassesService = glassesService;
//    }
//    @GetMapping("/api/v1/glasses")
//    public ResponseEntity<Glasses> getGlassesByNumber(@RequestParam(value = "number") int glassesNumber) {
//        Glasses result = glassesService.getGlassesByNumber(glassesNumber); //option null
//        if (result != null) {
//            return new ResponseEntity<>(glassesService.getGlassesByNumber(glassesNumber), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }



    @GetMapping(value = "/api/v1/glasses", produces = "application/json")
    public List<Glasses> getGlasses() {
        return glassesService.getGlasses();    }
    @GetMapping(value = "/api/v1/glasses/dto", produces = "application/json")
    public List<GlassesDto> getGlassesDto() {
        return glassesService.getGlassesDto();
    }
    @GetMapping(value = "/api/v1/glasses/dto/xml", produces = "application/xml")
    public List<GlassesDto> getGlassesDtoXml() {
        return glassesService.getGlassesDto();
    }
    @PostMapping( value = "/api/v1/glasses", produces = "application/json")
    public ResponseEntity<Glasses> addGlasses(@RequestBody Glasses glasses) {
        return ResponseEntity
                .ok()
                .header("example_header", "example_header_1")
                .body(glassesService.saveGlasses(glasses));
    }

    @PutMapping( value = "/api/v1/glasses", produces = "application/json")
    public ResponseEntity<Glasses> updateGlasses(@RequestParam(value = "number")int glassesNumber, @RequestBody Glasses glasses) {
        Glasses result = glassesService.updateGlasses(glassesNumber, glasses);
        if (result != null) {
            return ResponseEntity
                    .ok()
                    .header("example_header", "example_header_1")
                    .body(result);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping(value = "/api/v1/glasses", produces = "application/json")
    public ResponseEntity<?> deleteGlassesByNumber(@RequestParam(value = "number") int glassesNumber) {
        if (glassesService.deleteGlassesByNumber(glassesNumber)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/api/v1/glasses/download/file/xls/{filename}")
    public ResponseEntity<Resource> downloadXls(@PathVariable String filename) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException, IOException {
        CreatorXLS<Glasses> glassesFile = new CreatorXLS<>(Glasses.class);
        Resource resource = new FileSystemResource(glassesFile.createFile(filename, glassesService.getGlasses()));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/excel"))
                .header("Content-Disposition","attachment; filename=")
                .contentLength(resource.getFile().length())
                .body(resource);
    }









}
