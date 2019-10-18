package com.opticalstore.services;

import com.opticalstore.commons.mappers.AdressesMapper;
import com.opticalstore.models.Adresses;
import com.opticalstore.models.AdressesDto;
import com.opticalstore.models.Glasses;
import com.opticalstore.repositories.AdressesRepository;
import com.opticalstore.security.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdressesService {
    private AdressesMapper adressesMapper;
    private AdressesRepository adressesRepository;
    private UserAppRepository userAppRepository;

    public AdressesService(AdressesMapper adressesMapper, AdressesRepository adressesRepository, UserAppRepository userAppRepository) {
        this.adressesMapper = adressesMapper;
        this.adressesRepository = adressesRepository;
        this.userAppRepository = userAppRepository;
    }
    public List<AdressesDto> getAdressesDto() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        System.out.println(userApp.getId());
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());
        return currentUser
                .getAdresses()
                .stream()
                .map(adressesMapper::map)
                .collect(Collectors.toList());
    }

    public Adresses saveAdresses(Adresses adresses) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        System.out.println(userApp.getId());
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());
        Adresses adresses2= Adresses
                .builder()
                .name(adresses.getName())
                .surname(adresses.getSurname())
                .deliveryToTheCompany(adresses.isDeliveryToTheCompany())
                .firmName(adresses.getFirmName())
                .country(adresses.getCountry())
                .town(adresses.getTown())
                .street(adresses.getStreet())
                .apartmentNumber(adresses.getApartmentNumber())
                .zipCode(adresses.getZipCode())
                .userApp(currentUser)
                .build();
        return adressesRepository.save(adresses2);
    }
    public boolean deleteAdressesById(Long id) {
        return adressesRepository.deleteAdressesById(id) == 1; // 1 if success.
    }
    public Adresses getAdressesById(Long adressId) {
        return Optional
                .ofNullable(adressesRepository.findAdressById(adressId))
                .orElse(null);
    }




}
