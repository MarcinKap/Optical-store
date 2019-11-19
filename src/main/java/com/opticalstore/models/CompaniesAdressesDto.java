package com.opticalstore.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompaniesAdressesDto {
    private Long id;
    private String companyName;
    private int nipNumber;
    private String city;
    private String zipCode;
    private String street;
    private String apartmentNumber;
}
