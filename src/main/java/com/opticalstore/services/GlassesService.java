package com.opticalstore.services;


import com.opticalstore.commons.extras.CreatorXLS;
import com.opticalstore.mappers.GlassesMapper;
import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.models.GlassesSearchingForm;
import com.opticalstore.repositories.GlassRepository;
import org.springframework.data.domain.Sort;
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

    public Glasses getGlassesByNumber(Long glassNumber) {
        return Optional
                .ofNullable(glassRepository.findGlassesByNumber(glassNumber))
                .orElse(null);
    }

    public Glasses getGlassesById(Long id) {
        return Optional
                .ofNullable(glassRepository.findGlassesById(id))
                .orElse(null);
    }


    public List<Glasses> getGlassesByType(String type) {
        return Optional
                .ofNullable(glassRepository.findGlassesByType(type))
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


    public Glasses updateGlasses(Long id, Glasses glasses) {
        return Optional
                .ofNullable(glassRepository.findGlassesById(id))
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
                    return glassRepository.save(p); //zapis bezposrednio z repository
                })
                .orElse(null);
    }

    public void updateGlassesVoid(Long glassesNumber, Glasses glasses) {
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

    public boolean deleteGlassesById(Long id) {
        return glassRepository.deleteGlassesById(id) == 1; // 1 if success.
    }

    public void getFile(String filename) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException, IOException {
        CreatorXLS<Glasses> glassesFile = new CreatorXLS<>(Glasses.class);
        glassesFile.createFile(filename, getGlasses());
    }


//    public List <Glasses> sortGlassesbyParam (String parameter){
//        return glassRepository.findAll(Sort.by(parameter).ascending());
//    }


    public List<Glasses> sortGlassesbyParam(String parameter, Boolean ascending) {
        if (ascending) {
            return glassRepository.findAll(Sort.by(parameter).ascending());
        } else {
            return glassRepository.findAll(Sort.by(parameter).descending());
        }
    }


    public List<Glasses> getGlassesWithParams(Optional<GlassesSearchingForm> glassesSearchingForm, Optional<Boolean> ascOrDesc) {
        System.out.println("czy ja cos tu cokolwiek dostaje");

        //jeśli jest orderBy to sortujemy
        //jeśli jest searchingByParameter to nie wyszukujemy wszystkich tylko konkretne
//        if (!ascOrDesc.isPresent()) {
//            ascOrDesc = Optional.of(true);
//        }


//        System.out.println("polaryzajca" + glassesSearchingForm.get().getPolarization());
//        System.out.println("marks" + glassesSearchingForm.get().getGlassesMarks());
//        System.out.println("gender" + glassesSearchingForm.get().getGlassesGender());
//        System.out.println("number" +glassesSearchingForm.get().getGlassesNumber());
//        System.out.println("type" +glassesSearchingForm.get().getGlassesType());
//        System.out.println("form" +glassesSearchingForm.get().getForm());


        System.out.println("polaryzacja " + glassesSearchingForm.get().getPolarization());


        if (glassesSearchingForm.get().getOrderBy() != null) {
            if (ascOrDesc.get()) {
                if (!checkingParameterPresence(glassesSearchingForm)) {
                    System.out.println("Opcja 1");

//                    return glassRepository.findAllByGlassesTypeAndGlassesNumberAndGlassesGenderAndFormAndPolarization(glassesSearchingForm.get().getGlassesType(), glassesSearchingForm.get().getGlassesNumber(), glassesSearchingForm.get().getGlassesGender(), glassesSearchingForm.get().getGlassesMarks(), glassesSearchingForm.get().getForm(), glassesSearchingForm.get().getPolarization(), Sort.by(Sort.Direction.ASC, glassesSearchingForm.get().getOrderBy().get()));
                    return glassRepository.findAllByGlassesSearchingFormWithSort(
                            glassesSearchingForm.get().getGlassesType(),
                            glassesSearchingForm.get().getGlassesNumber(),
                            glassesSearchingForm.get().getGlassesGender(),
                            glassesSearchingForm.get().getGlassesMarks(),
                            glassesSearchingForm.get().getForm(),
                            glassesSearchingForm.get().getPolarization(),
                            glassesSearchingForm.get().getWidthOfTheLensLowerLimit(),
                            glassesSearchingForm.get().getWidthOfTheLensUpperLimit(),
                            glassesSearchingForm.get().getPriceLowerLimit(),
                            glassesSearchingForm.get().getPriceUpperLimit(),
                            Sort.by(Sort.Direction.ASC, glassesSearchingForm.get().getOrderBy().get()));
//                    return glassRepository.findAllByGlassesTypeOrGlassesNumberOrGlassesGenderOrFormOrPolarization(glassesSearchingForm.get().getGlassesType(), glassesSearchingForm.get().getGlassesNumber(), glassesSearchingForm.get().getGlassesGender(), glassesSearchingForm.get().getForm(), glassesSearchingForm.get().getPolarization(), Sort.by(Sort.Direction.ASC, glassesSearchingForm.get().getOrderBy().get()));
                } else {
                    System.out.println("Opcja 2");
                    return glassRepository.findAll(Sort.by(Sort.Direction.ASC, glassesSearchingForm.get().getOrderBy().get()));

                }
            } else {
                if (!checkingParameterPresence(glassesSearchingForm)) {
                    System.out.println("Opcja 3");
                    return glassRepository.findAllByGlassesSearchingFormWithSort(
                            glassesSearchingForm.get().getGlassesType(),
                            glassesSearchingForm.get().getGlassesNumber(),
                            glassesSearchingForm.get().getGlassesGender(),
                            glassesSearchingForm.get().getGlassesMarks(),
                            glassesSearchingForm.get().getForm(),
                            glassesSearchingForm.get().getPolarization(),
                            glassesSearchingForm.get().getWidthOfTheLensLowerLimit(),
                            glassesSearchingForm.get().getWidthOfTheLensUpperLimit(),
                            glassesSearchingForm.get().getPriceLowerLimit(),
                            glassesSearchingForm.get().getPriceUpperLimit(),
                            Sort.by(Sort.Direction.DESC, glassesSearchingForm.get().getOrderBy().get()));


//                    return glassRepository.findAllByGlassesTypeAndGlassesNumberAndGlassesGenderAndFormAndPolarization(glassesSearchingForm.get().getGlassesType(), glassesSearchingForm.get().getGlassesNumber(), glassesSearchingForm.get().getGlassesGender(), glassesSearchingForm.get().getGlassesMarks(), glassesSearchingForm.get().getForm(), glassesSearchingForm.get().getPolarization(), Sort.by(Sort.Direction.DESC, glassesSearchingForm.get().getOrderBy().get()));
//                    return glassRepository.findAllByGlassesTypeOrGlassesNumberOrGlassesGenderOrFormOrPolarization(glassesSearchingForm.get().getGlassesType(), glassesSearchingForm.get().getGlassesNumber(), glassesSearchingForm.get().getGlassesGender(), glassesSearchingForm.get().getForm(), glassesSearchingForm.get().getPolarization(), Sort.by(Sort.Direction.DESC, glassesSearchingForm.get().getOrderBy().get()));
                } else {
                    System.out.println("Opcja 4");
                    return glassRepository.findAll(Sort.by(Sort.Direction.DESC, glassesSearchingForm.get().getOrderBy().get()));
                }
            }

        } else {
            if (!checkingParameterPresence(glassesSearchingForm)) {
                System.out.println("Opcja 5");
                return glassRepository.findAllByGlassesSearchingForm(
                        glassesSearchingForm.get().getGlassesType(),
                        glassesSearchingForm.get().getGlassesNumber(),
                        glassesSearchingForm.get().getGlassesGender(),
                        glassesSearchingForm.get().getGlassesMarks(),
                        glassesSearchingForm.get().getForm(),
                        glassesSearchingForm.get().getPolarization(),
                        glassesSearchingForm.get().getWidthOfTheLensLowerLimit(),
                        glassesSearchingForm.get().getWidthOfTheLensUpperLimit(),
                        glassesSearchingForm.get().getPriceLowerLimit(),
                        glassesSearchingForm.get().getPriceUpperLimit());

                //                return glassRepository.findAllByGlassesTypeOrGlassesNumberOrGlassesGenderOrFormOrPolarization(glassesSearchingForm.get().getGlassesType(), glassesSearchingForm.get().getGlassesNumber(), glassesSearchingForm.get().getGlassesGender(), glassesSearchingForm.get().getForm(), glassesSearchingForm.get().getPolarization());
            } else {
                System.out.println("Opcja 6");
                return glassRepository.findAll();
            }


        }
    }

    public Optional<Boolean> ascOrDescSettings(Optional<Boolean> ascOrDesc) {
        System.out.println("ascordesc" + ascOrDesc);
        if (ascOrDesc == null) {
            return Optional.of(true);
        } else {
            return Optional.of(!ascOrDesc.get());
        }
    }

    public boolean checkingParameterPresence(Optional<GlassesSearchingForm> glassesSearchingForm) {

        if (glassesSearchingForm.get().getGlassesType() == null
                && glassesSearchingForm.get().getForm() == null
                && glassesSearchingForm.get().getGlassesGender() == null
                && glassesSearchingForm.get().getGlassesNumber() == null
                && glassesSearchingForm.get().getGlassesMarks() == null
                && glassesSearchingForm.get().getPolarization() == null
                && glassesSearchingForm.get().getWidthOfTheLensLowerLimit() == null
                && glassesSearchingForm.get().getWidthOfTheLensUpperLimit() == null
                && glassesSearchingForm.get().getPriceLowerLimit() == null
                && glassesSearchingForm.get().getPriceUpperLimit() == null
        ) {
            return true;
        } else {
            return false;
        }

    }
}
