package com.opticalstore.services;

import com.opticalstore.commons.mappers.CountriesMapper;
import com.opticalstore.models.AdressesDto;
import com.opticalstore.models.Countries;
import com.opticalstore.models.CountriesDto;
import com.opticalstore.repositories.CountriesRepository;
import com.opticalstore.security.UserApp;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountriesService {

    CountriesMapper countriesMapper;
    CountriesRepository countriesRepository;

    public CountriesService(CountriesMapper countriesMapper, CountriesRepository countriesRepository) {
        this.countriesMapper = countriesMapper;
        this.countriesRepository = countriesRepository;
    }
    public List<CountriesDto> getCountriesDto() {
        return countriesRepository
                .findAll()
                .stream()
                .map(countriesMapper::map)
                .collect(Collectors.toList());
    }
    public List<Countries> getCountries() {
        return countriesRepository.findAll();
    }
}
