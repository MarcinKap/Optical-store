package com.opticalstore.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GlassesDto {
    private Long id;
    private Long glassesNumber;
    private String glassesType;
    private String glassesGender;
    private String form;
    private double price;
    private Boolean polarization;
    private int widthOfTheLens;
    private String glassesImage;
    private String glassesMarks;
}
