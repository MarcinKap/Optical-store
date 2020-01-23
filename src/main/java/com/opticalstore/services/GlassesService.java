package com.opticalstore.services;


import com.opticalstore.commons.extras.CreatorXLS;
import com.opticalstore.mappers.GlassesMapper;
import com.opticalstore.models.Glasses;
import com.opticalstore.models.GlassesDto;
import com.opticalstore.models.GlassesSearchingForm;
import com.opticalstore.repositories.GlassRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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



    public List<Glasses> getGlassesWithParams(Optional<GlassesSearchingForm> glassesSearchingForm, Optional<Boolean> ascOrDesc) {
        if (glassesSearchingForm.get().getOrderBy() != null) {
            if (ascOrDesc.get()) {
                if (!checkingParameterPresence(glassesSearchingForm)) {
                    System.out.println("Opcja 1");
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






    public Page<Glasses> getGlassesWithParamsPaginated(
            Optional<GlassesSearchingForm> glassesSearchingForm,
            Optional<Boolean> ascOrDesc,
            Optional<Integer> page,
            Optional<Integer> pageSize) {

        System.out.println("pagesize przed: " + pageSize);

        int currentPage = (page).orElse(1)-1;
        Integer currentPageSize = pageSize.orElse(4);
        System.out.println("pagesize: " + pageSize);

        if (glassesSearchingForm.get().getOrderBy() != null) {
            if (ascOrDesc.get()) {
                if (!checkingParameterPresence(glassesSearchingForm)) {
                    System.out.println("Opcja 1");
                    return glassRepository.findAllByGlassesSearchingFormWithSortWithPaginating(
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
                            PageRequest.of(
                                    currentPage,
                                    currentPageSize,
                                    Sort.by(glassesSearchingForm.get().getOrderBy().get()).ascending().and(Sort.by(glassesSearchingForm.get().getOrderBy().get()))));
                } else {
                    System.out.println("Opcja 2");
                    return glassRepository.findAll(PageRequest.of(
                            currentPage,
                            currentPageSize,
                            Sort.by(glassesSearchingForm.get().getOrderBy().get()).ascending().and(Sort.by(glassesSearchingForm.get().getOrderBy().get()))));
                }
            } else {
                if (!checkingParameterPresence(glassesSearchingForm)) {
                    System.out.println("Opcja 3");
                    return glassRepository.findAllByGlassesSearchingFormWithSortWithPaginating(
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
                            PageRequest.of(
                                    currentPage,
                                    currentPageSize,
                                    Sort.by(glassesSearchingForm.get().getOrderBy().get()).descending().and(Sort.by(glassesSearchingForm.get().getOrderBy().get()))));
                } else {
                    System.out.println("Opcja 4");
                    return glassRepository.findAll(PageRequest.of(
                            currentPage,
                            currentPageSize,
                            Sort.by(glassesSearchingForm.get().getOrderBy().get()).descending().and(Sort.by(glassesSearchingForm.get().getOrderBy().get()))));
                }
            }
        } else {
            if (!checkingParameterPresence(glassesSearchingForm)) {
                System.out.println("Opcja 5");
                return glassRepository.findAllByGlassesSearchingFormWithPaginating(
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
                        PageRequest.of(currentPage, currentPageSize));
            } else {
                System.out.println("Opcja 6");
                return glassRepository.findAll(PageRequest.of(currentPage, currentPageSize));
            }
        }
    }


}
