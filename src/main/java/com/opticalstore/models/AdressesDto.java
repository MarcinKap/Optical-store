package com.opticalstore.models;


import com.opticalstore.security.UserApp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdressesDto {


    private String name;
    private String surname;
//    private boolean deliveryToTheCompany;
//    private String firmName;
//    private String country;
//    private String town;
//    private String street;
//    private int apartmentNumber;
//    private String zipCode;
//    @ManyToOne
//    private UserApp user;


}
