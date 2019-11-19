package com.opticalstore.commons.mappers;


import com.opticalstore.models.CompaniesAdresses;
import com.opticalstore.models.CompaniesAdressesDto;
import org.springframework.stereotype.Component;

@Component
public class CompaniesAdressesMapper implements Mapper<CompaniesAdresses, CompaniesAdressesDto> {

    @Override
    public CompaniesAdressesDto map(CompaniesAdresses from) {
        return CompaniesAdressesDto
                .builder()
                .companyName(from.getCompanyName())
                .nipNumber(from.getNipNumber())
                .city(from.getCity())
                .zipCode(from.getZipCode())
                .street(from.getStreet())
                .apartmentNumber(from.getApartmentNumber())
                .id(from.getId())
                .build();
    }

    @Override
    public CompaniesAdresses reverseMap(CompaniesAdressesDto to) {
        return CompaniesAdresses
                .builder()
                .companyName(to.getCompanyName())
                .nipNumber(to.getNipNumber())
                .city(to.getCity())
                .zipCode(to.getZipCode())
                .street(to.getStreet())
                .apartmentNumber(to.getApartmentNumber())
                .id(to.getId())
                .build();
    }
}
