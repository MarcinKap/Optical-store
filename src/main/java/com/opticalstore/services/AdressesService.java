package com.opticalstore.services;

import com.opticalstore.commons.mappers.AdressesMapper;
import com.opticalstore.models.Adresses;
import com.opticalstore.models.AdressesDto;
import com.opticalstore.repositories.AdressesRepository;
import com.opticalstore.security.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        UserApp userApp = (UserApp) authentication.getPrincipal();

//        List<Integer> adressesId = adressesRepository.findUserAdresses(userApp.getId());
//        System.out.println(adressesId);

        List<AdressesDto> adressesDtoList = adressesRepository
                .findAll()
                .stream()
                .map(adressesMapper::map)
                .collect(Collectors.toList());


        return adressesDtoList;
    }

    public Adresses saveAdresses(Adresses adresses) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        System.out.println(userApp.getId());
        UserApp userApps123 = userAppRepository.findUserAppById(userApp.getId());

        Adresses adresses2= Adresses
                .builder()
                .name(adresses.getName())
                .surname(adresses.getSurname())
                .userApp123(userApps123)
                .build();

        return adressesRepository.save(adresses2);
    }
    public boolean deleteAdressesByName(String adressesName) {
        return adressesRepository.deleteAdressesByName(adressesName) == 1; // 1 if success.
    }











    //
//    public AdressesService(AdressesMapper adressesMapper, AdressesRepository adressesRepository) {
//        this.adressesMapper = adressesMapper;
//        this.adressesRepository = adressesRepository;
//    }
//
//    public List<AdressesDto> getAdressesDto() {
//        return adressesRepository
//                .findAll()
//                .stream()
//                .map(adressesMapper::map)
//                .collect(Collectors.toList());
//    }
//
//    public Adresses saveAdresses(Adresses adresses) {
//
//        System.out.println("weszlismy do save adresses");
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
//        UserApp userApp = (UserApp) authentication.getPrincipal();
//
//        Adresses adresses2 = Adresses
//                .builder()
////                .userApp(userApp)
//                .name(adresses.getName())
//                .surname(adresses.getSurname())
//                .build();
//
//        System.out.println("jestesmy w serwisie");
//        return adressesRepository.save(adresses2);
//    }
//private UserAppRepository userAppRepository;
//
//    public Adresses saveAdresses(AdressesDto adressesDto) {
//
//        System.out.println("2gi etap");
//        System.out.println(adressesDto);
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//
//
//
//        UserApp user123 = userAppRepository
//                .findUserAppByName(currentPrincipalName)
//                .get();
//        ////////
//        System.out.println("etap 3");
//        System.out.println(user123);
//
//        Adresses adressesFinal = Adresses
//                .builder()
//                .name(adressesDto.getName())
//                .surname(adressesDto.getSurname())
//                .userApp(user123)
//                .build();
//        return adressesRepository.save(adressesFinal);
//    }

    //do poprawy
//    public boolean deleteAdressesByBuilder(String formName) {
//        return adressesRepository.deleteAdressesByName(formName) == 1; // 1 if success.
//    }


}
