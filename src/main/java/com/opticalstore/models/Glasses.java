package com.opticalstore.models;


import lombok.*;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "glasses")
public class Glasses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "glasses_number", nullable = false, unique = true)
    private int glassesNumber;

    @Column(name = "glasses_type")
    private String glassesType;

    @Column(name = "glasses_gender")
    private String glassesGender;

    @Column(name = "form")
    private String form;

    @Column(name = "price")
    private double price;

    @Column(name = "polarization")
    private Boolean polarization;

    @Column(name = "width_of_the_lens")
    private int widthOfTheLens;

    @Column(name = "glasses_image")
    private String glassesImage;

    @Column(name = "glasses_marks")
    private String glassesMarks;
}
