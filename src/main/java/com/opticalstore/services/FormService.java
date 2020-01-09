package com.opticalstore.services;

import com.opticalstore.mappers.FormMapper;
import com.opticalstore.models.Form;
import com.opticalstore.models.FormDto;
import com.opticalstore.repositories.FormRepository;
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
    public Form saveForm(Form form) {
        return formRepository.save(form);
    }
    public boolean deleteFormsByName(String formName) {
        return formRepository.deleteFormByName(formName) == 1; // 1 if success.
    }
}
