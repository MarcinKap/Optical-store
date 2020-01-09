package com.opticalstore.mappers;

import com.opticalstore.models.Form;
import com.opticalstore.models.FormDto;
import org.springframework.stereotype.Component;

@Component
public class FormMapper implements Mapper<Form, FormDto> {
    @Override
    public FormDto map(Form from) {
        return FormDto
                .builder()
                .form(from.getForm())
                .build();
    }
    @Override
    public Form reverseMap(FormDto to) {
        return Form
                .builder()
                .form(to.getForm())
                .build();
    }
}
