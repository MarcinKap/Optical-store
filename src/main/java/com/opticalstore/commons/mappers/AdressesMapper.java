package com.opticalstore.commons.mappers;

import com.opticalstore.models.Adresses;
import com.opticalstore.models.AdressesDto;
import org.springframework.stereotype.Component;

@Component
public class AdressesMapper implements Mapper<Adresses, AdressesDto> {
    @Override
    public AdressesDto map(Adresses from) {
        return AdressesDto
                .builder()
                .name(from.getName())
                .surname(from.getSurname())
                .deliveryToTheCompany(from.isDeliveryToTheCompany())
                .firmName(from.getFirmName())
                .country(from.getCountry())
                .town(from.getTown())
                .street(from.getStreet())
                .apartmentNumber(from.getApartmentNumber())
                .zipCode(from.getZipCode())
                .id(from.getId())
                .build();
    }
    @Override
    public Adresses reverseMap(AdressesDto to) {
        return Adresses
                .builder()
                .name(to.getName())
                .surname(to.getSurname())
                .deliveryToTheCompany(to.isDeliveryToTheCompany())
                .firmName(to.getFirmName())
                .country(to.getCountry())
                .town(to.getTown())
                .street(to.getStreet())
                .apartmentNumber(to.getApartmentNumber())
                .zipCode(to.getZipCode())
                .id(to.getId())
                .build();
    }



}
