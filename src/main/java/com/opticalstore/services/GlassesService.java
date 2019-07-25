package com.opticalstore.services;


import com.opticalstore.commons.mappers.GlassesMapper;
import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.models.GlassesMark;
import com.opticalstore.repositories.GlassRepository;
import org.springframework.stereotype.Service;

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

        System.out.println(glassesType);
//        System.out.println(glassesGender);
//        System.out.println(form);
//        System.out.println(priceLowerLimit);
//        System.out.println(priceUpperLimit);
//        System.out.println(polarization);
//        System.out.println(widthOfTheLensLowerLimit);
//        System.out.println(widthOfTheLensUpperLimit);
//        System.out.println(glassesMarks);

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

//    public List<Glasses> getGlassesByParam(String glassesType,
//                                           String glassesGender,
//                                           String form,
//                                           double price,
//                                           boolean polarization,
//                                           int widthOfTheLens,
//                                           String glassesMarks) {
//        return Optional
//                .ofNullable(glassRepository.findGlassesByParam(glassesType))
//                .orElse(null);
//    }




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

//    public List<Glasses> getGlasses(String param) {
//        return glassRepository.findGlassesByParam(param);
//    }

    public Glasses saveGlasses(Glasses glasses) {
        return glassRepository.save(glasses);
    }

//    public Glasses searchGlasses(Glasses glasses) {
//        return glassRepository.get(glasses);
//    }



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

    //inne podejscie do update obiektu w bazie badych z void
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

//               -------- helpers --------

//    private Planet planetNameToUpperCase(Planet p) {
//        p.setPlanetName(p.getPlanetName().toUpperCase());
//        return p;
//    }
//
//    private Planet updatePlanetResult(Planet p) {
//        return Planet
//                .builder()
//                .id(p.getId())
//                .planetName(p.getPlanetName())
//                .planetType(p.getPlanetType())
//                .planetInfo(p.getPlanetInfo())
//                .planetImage(p.getPlanetImage())
//                .oneWayLightTimeToTheSun(p.getOneWayLightTimeToTheSun())
//                .lengthOfYear(p.getLengthOfYear())
//                .distanceFromSun(p.getDistanceFromSun())
//                .build();
//    }








}
