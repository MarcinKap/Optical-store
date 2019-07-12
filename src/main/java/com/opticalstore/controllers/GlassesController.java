package com.opticalstore.controllers;


import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.models.GlassesMark;
import com.opticalstore.services.GlassesService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

//@CrossOrigin
@RestController
@RequestMapping("/")
public class GlassesController {

    private GlassesService glassesService;

    public GlassesController(GlassesService glassesService) {
        this.glassesService = glassesService;
    }

    @GetMapping("/api/v1/glasses")
    public ResponseEntity<Glasses> getGlassesByNumber(@RequestParam(value = "number") String glassesNumber) {
        Glasses result = glassesService.getGlassesByNumber(glassesNumber); //option null
        if (result != null) {
            return new ResponseEntity<>(glassesService.getGlassesByNumber(glassesNumber), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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
    public ResponseEntity<Glasses> updateGlasses(@RequestParam(value = "number")String glassesNumber, @RequestBody Glasses glasses) {
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

//
//    @RequestMapping(value = { "/selectOptionExample1" }, method = RequestMethod.GET)
//    public String selectOptionExample1Page(Model model) {
//
//        Glasses glasses_model = new Glasses();
//        model.addAttribute("glasses_model", glasses_model);
//
//        List<String> list = glasses_model.getGlassesMarks();
//        model.addAttribute("countries", list);
//
//        return "selectOptionExample1";
//    }




}
