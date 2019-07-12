package com.opticalstore.services;

import com.opticalstore.commons.mappers.FormMapper;
import com.opticalstore.commons.mappers.MarksMapper;
import com.opticalstore.models.FormDto;
import com.opticalstore.models.GlassesMarkDto;
import com.opticalstore.repositories.FormRepository;
import com.opticalstore.repositories.MarksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormService {

    private FormRepository formRepository;
    private FormMapper formMapper;

    public FormService(FormRepository formRepository, FormMapper formMapper) {
        this.formRepository = formRepository;
        this.formMapper = formMapper;
    }

    public List<FormDto> getFormDto() {
        return formRepository
                .findAll()
                .stream()
                .map(formMapper::map)
                .collect(Collectors.toList());
    }



}
