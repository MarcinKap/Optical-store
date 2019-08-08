package com.opticalstore.services;


import com.opticalstore.commons.extras.CreatorXLS;
import com.opticalstore.commons.mappers.GlassesMapper;
import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.models.GlassesMark;
import com.opticalstore.repositories.GlassRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GlassesService {

private GlassRepository glassRepository;
private GlassesMapper glassesMapper;

    public GlassesService(GlassRepository glassRepository, GlassesMapper glassesMapper) {
        this.glassRepository = glassRepository;
        this.glassesMapper = glassesMapper;
    }

    public Glasses getGlassesByNumber(int glassNumber) {

        return Optional
                .ofNullable(glassRepository.findGlassesByNumber(glassNumber))
                .orElse(null);
    }

    public List<Glasses> getGlassesByParam(String glassesType,
                                           String glassesGender,
                                           String form,
                                           double priceLowerLimit,
                                           double priceUpperLimit,
                                           Boolean polarization,
                                           int widthOfTheLensLowerLimit,
                                           int widthOfTheLensUpperLimit,
                                           String glassesMarks) {
        return Optional
                .ofNullable(glassRepository.findGlassesByParam(
                        glassesType,
                        glassesGender,
                        form,
                        priceLowerLimit,
                        priceUpperLimit,
                        polarization,
                        widthOfTheLensLowerLimit,
                        widthOfTheLensUpperLimit,
                        glassesMarks))
                .orElse(null);
    }

    public List<Glasses> getGlasses() {
        return glassRepository.findAll();
    }

    public List<GlassesDto> getGlassesDto() {
        return glassRepository
                .findAll()
                .stream()
                .map(glassesMapper::map)
                .collect(Collectors.toList());
    }


    public Glasses saveGlasses(Glasses glasses) {
        return glassRepository.save(glasses);
    }




    public Glasses updateGlasses(int glassesNumber, Glasses glasses) {
        System.out.println("czy to sie zapisuje czy nie");
        //glassRepository.findGlassesByNumber(glassesNumber).setGlassesMarks(glasses.getGlassesMarks());
       // System.out.println(glassRepository.findGlassesByNumber(glassesNumber).getGlassesGender());
        return Optional
                .ofNullable(glassRepository.findGlassesByNumber(glassesNumber))
                .map(p -> {
                    p.setGlassesNumber(glasses.getGlassesNumber());
                    p.setForm(glasses.getForm());
                    p.setGlassesGender(glasses.getGlassesGender());
                    p.setGlassesImage(glasses.getGlassesImage());
                    p.setPolarization(glasses.getPolarization());
                    p.setPrice(glasses.getPrice());
                    p.setWidthOfTheLens(glasses.getWidthOfTheLens());
                    p.setGlassesType(glasses.getGlassesType());
                    p.setGlassesMarks(glasses.getGlassesMarks());

                  //  return saveGlasses(p);//uzycie wlasnej metody z servisu
                       return glassRepository.save(p); //zapis bezposrednio z repository
                })
                .orElse(null);
    }

    public void updateGlassesVoid(int glassesNumber, Glasses glasses) {
        Optional.ofNullable(glassRepository.findGlassesByNumber(glassesNumber))
                .ifPresent(p -> {
                    p.setForm(glasses.getForm());
                    p.setGlassesGender(glasses.getGlassesGender());
                    p.setGlassesImage(glasses.getGlassesImage());
                    p.setGlassesNumber(glasses.getGlassesNumber());
                    p.setPolarization(glasses.getPolarization());
                    p.setPrice(glasses.getPrice());
                    p.setWidthOfTheLens(glasses.getWidthOfTheLens());
                    p.setGlassesType(glasses.getGlassesType());
                    p.setGlassesMarks(glasses.getGlassesMarks());

                    glassRepository.save(p);
                });
    }

    public boolean deleteGlassesByNumber(int glassesNumber) {
        return glassRepository.deleteGlassesByNumber(glassesNumber) == 1; // 1 if success.
    }


    public void getFile(String filename) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException, IOException {
        CreatorXLS<Glasses> glassesFile = new CreatorXLS<>(Glasses.class);
        glassesFile.createFile(filename, getGlasses());
    }

}
