package com.opticalstore.services;

import com.opticalstore.mappers.AdressesMapper;
import com.opticalstore.models.Adresses;
import com.opticalstore.models.AdressesDto;
import com.opticalstore.repositories.AdressesRepository;
import com.opticalstore.security.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());
        Adresses newAdress= Adresses
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
        return adressesRepository.save(newAdress);
    }
    public boolean deleteAdressesById(Long id) {
        return adressesRepository.deleteAdressesById(id) == 1; // 1 if success.
    }
    public Adresses getAdressesById(Long adressId) {
        return adressesRepository.findAdressById(adressId);
    }
    public Adresses updateAdresses(Long adressesId, Adresses adresses) {
        return Optional
                .ofNullable(adressesRepository.findAdressById(adressesId))
                .map(a -> {
                    a.setId(adresses.getId());
                    a.setName(adresses.getName());
                    a.setSurname(adresses.getSurname());
                    a.setDeliveryToTheCompany(adresses.isDeliveryToTheCompany());
                    a.setFirmName(adresses.getFirmName());
                    a.setCountry(adresses.getCountry());
                    a.setTown(adresses.getTown());
                    a.setStreet(adresses.getStreet());
                    a.setApartmentNumber(adresses.getApartmentNumber());
                    a.setZipCode(adresses.getZipCode());
                    return adressesRepository.save(a); //zapis bezposrednio z repository
                })
                .orElse(null);
    }
}
