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
                .build();
//                .deliveryToTheCompany(from.isDeliveryToTheCompany())
//                .firmName(from.getFirmName())
//                .country(from.getCountry())
//                .town(from.getTown())
//                .street(from.getStreet())
//                .apartmentNumber(from.getApartmentNumber())
//                .zipCode(from.getZipCode())

    }


    @Override
    public Adresses reverseMap(AdressesDto to) {
//        System.out.println("jestesmy w reversemap");
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        UserApp userApp = (UserApp) authentication.getPrincipal();


        return Adresses
                .builder()
                .name(to.getName())
                .surname(to.getSurname())
                .build();
//                .deliveryToTheCompany(to.isDeliveryToTheCompany())
//                .firmName(to.getFirmName())
//                .country(to.getCountry())
//                .town(to.getTown())
//                .street(to.getStreet())
//                .apartmentNumber(to.getApartmentNumber())
//                .zipCode(to.getZipCode())
//                .userApp(userApp)

    }


}
