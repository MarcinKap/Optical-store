package com.opticalstore.services;


import com.opticalstore.commons.extras.CreatorXLS;
import com.opticalstore.mappers.GlassesMapper;
import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesDto;
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


    public List<Glasses> getGlassesWithParams(Optional<String> orderBy, Optional<Boolean> ascOrDesc, Optional<String> searchingByParameter, Optional<String> searchingByValue ) {
        System.out.println("czy ja cos tu cokolwiek dostaje");

        //jeśli jest orderBy to sortujemy
        //jeśli jest searchingByParameter to nie wyszukujemy wszystkich tylko konkretne
//        if (!ascOrDesc.isPresent()) {
//            ascOrDesc = Optional.of(true);
//        }

        System.out.println("ascOrDesc wynosi: " + ascOrDesc.get());
        if (orderBy.isPresent()) {
            System.out.println("orderBy to:" + orderBy.get());
            System.out.println("g." + orderBy.get());
        } else {
            System.out.println("nie ma orderBy");
        }
        if (searchingByParameter.isPresent()) {
            System.out.println("searchingByParameter to: " + searchingByParameter.get());
        } else {
            System.out.println("nie ma searchingByParameter");
        }
        if(searchingByValue.isPresent()){
            System.out.println("searchingByValue to: " + searchingByValue.get());
        }else {
            System.out.println("nie ma searchingByValue");
        }

        if (ascOrDesc.get()) {
            if (orderBy.isPresent()) {
                if (searchingByParameter.isPresent() && !searchingByParameter.get().isEmpty()) {
                    //orderBy daje infor o tym przez co bedziemy sortować
                    //muszę znalezc metodę która przyjmie dla Stringa searchingByParameter i otrzymam okulary tylko po glassestypie oraz bede mogl podac orderBy
                    System.out.println("opcja 1");
                    return glassRepository.findAllByGlassesType(searchingByValue.get(), Sort.by(orderBy.get()).ascending());
//                    return glassRepository.findAllByGlassesTypeOrGlassesNumberOrGlassesGenderOrFormOrPriceOrPolarizationOrWidthOfTheLensOrGlassesMarks(searchingByValue.get(), Sort.by(orderBy.get()).ascending());
                } else {
                    System.out.println("opcja 2");
                    return glassRepository.findAll(Sort.by(orderBy.get()).ascending());
                }
            } else {
                if (searchingByParameter.isPresent() && !searchingByParameter.get().isEmpty()) {
                    System.out.println("opcja 3");
                    return glassRepository.findGlassesByType(searchingByValue.get());
                } else {
                    System.out.println("opcja 4");
                    return glassRepository.findAll();
                }
            }
        } else {
            if (orderBy.isPresent()) {
                if (searchingByParameter.isPresent() && !searchingByParameter.get().isEmpty()) {
                    System.out.println("opcja 5");
//                    return glassRepository.findById()
                    return glassRepository.findAllByGlassesType(searchingByValue.get(), Sort.by(orderBy.get()).descending());
                } else {
                    System.out.println("opcja 6");
                    return glassRepository.findAll(Sort.by(orderBy.get()).descending());
                }
            } else {
                if (searchingByParameter.isPresent() && !searchingByParameter.get().isEmpty()) {
                    System.out.println("opcja 7");
                    return glassRepository.findGlassesByType(searchingByValue.get());
                } else {
                    System.out.println("opcja 8");
                    return glassRepository.findAll();
                }
            }
        }


    }
}
