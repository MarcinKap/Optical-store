package com.opticalstore.services;


import com.opticalstore.mappers.CompaniesAdressesMapper;
import com.opticalstore.models.CompaniesAdresses;
import com.opticalstore.models.CompaniesAdressesDto;
import com.opticalstore.repositories.CompaniesAdressesRepository;
import com.opticalstore.security.UserApp;
import com.opticalstore.security.UserAppRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompaniesAdressesService {


    private CompaniesAdressesMapper companiesAdressesMapper;
    private CompaniesAdressesRepository companiesAdressesRepository;
    private UserAppRepository userAppRepository;

    public CompaniesAdressesService(CompaniesAdressesMapper companiesAdressesMapper, CompaniesAdressesRepository companiesAdressesRepository, UserAppRepository userAppRepository) {
        this.companiesAdressesMapper = companiesAdressesMapper;
        this.companiesAdressesRepository = companiesAdressesRepository;
        this.userAppRepository = userAppRepository;
    }

    public List<CompaniesAdressesDto> getCompaniesAdressesDto() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());
        return currentUser
                .getCompanies_adresses()
                .stream()
                .map(companiesAdressesMapper::map)
                .collect(Collectors.toList());
    }

    public CompaniesAdresses saveCompaniesAdresses(CompaniesAdresses companiesAdresses) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        UserApp currentUser = userAppRepository.findUserAppById(userApp.getId());
        CompaniesAdresses newAdress= CompaniesAdresses
                .builder()
                .companyName(companiesAdresses.getCompanyName())
                .nipNumber(companiesAdresses.getNipNumber())
                .city(companiesAdresses.getCity())
                .zipCode(companiesAdresses.getZipCode())
                .street(companiesAdresses.getStreet())
                .apartmentNumber(companiesAdresses.getApartmentNumber())
                .userApp(currentUser)
                .build();
        return companiesAdressesRepository.save(newAdress);
    }
    public boolean deleteCompaniesAdressesById(Long id) {
        return companiesAdressesRepository.deleteCompaniesAdressesById(id) == 1; // 1 if success.
    }
    public CompaniesAdresses getCompaniesAdressesById(Long adressId) {
        return companiesAdressesRepository.findCompaniesAdressesById(adressId);
    }
    public CompaniesAdresses updateCompaniesAdresses(Long adressesId, CompaniesAdresses companiesAdresses) {
        return Optional
                .ofNullable(companiesAdressesRepository.findCompaniesAdressesById(adressesId))
                .map(a -> {
                    a.setCompanyName(companiesAdresses.getCompanyName());
                    a.setNipNumber(companiesAdresses.getNipNumber());
                    a.setCity(companiesAdresses.getCity());
                    a.setZipCode(companiesAdresses.getZipCode());
                    a.setStreet(companiesAdresses.getStreet());
                    a.setApartmentNumber(companiesAdresses.getApartmentNumber());
                    return companiesAdressesRepository.save(a); //zapis bezposrednio z repository
                })
                .orElse(null);
    }




}
