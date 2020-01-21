package com.opticalstore.models;

import com.sun.javafx.scene.control.behavior.OptionalBoolean;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Optional;
import java.util.OptionalLong;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class GlassesSearchingForm {

    private Optional<String> orderBy;
    private Optional<Boolean> ascOrDesc;
    private Optional<Long> glassesNumber;
    private Optional<String> glassesType;
    private Optional<String> glassesGender;
    private Optional<String> glassesMarks;
    private Optional<String> form;
    private Optional<Boolean> polarization;
    private Optional<Integer> widthOfTheLensLowerLimit;
    private Optional<Integer> widthOfTheLensUpperLimit;
    private Optional<Double> priceLowerLimit;
    private Optional<Double> priceUpperLimit;

    




}
