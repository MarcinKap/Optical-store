package com.opticalstore.commons.mappers;


import com.opticalstore.models.Countries;
import com.opticalstore.models.CountriesDto;
import org.springframework.stereotype.Component;

@Component
public class CountriesMapper implements Mapper<Countries, CountriesDto> {
@Override
    public CountriesDto map (Countries from){
    return CountriesDto
            .builder()
            .id(from.getId())
            .name(from.getName())
            .iso3(from.getIso3())
            .iso2(from.getIso2())
            .phonecode(from.getPhonecode())
            .capital(from.getCapital())
            .currency(from.getCurrency())
            .created_at(from.getCreated_at())
            .updated_at(from.getUpdated_at())
            .flag(from.getFlag())
            .wikiDataId(from.getWikiDataId())
            .build();
}
@Override
    public Countries reverseMap (CountriesDto to){
    return Countries
            .builder()
            .id(to.getId())
            .name(to.getName())
            .iso3(to.getIso3())
            .iso2(to.getIso2())
            .phonecode(to.getPhonecode())
            .capital(to.getCapital())
            .currency(to.getCurrency())
            .created_at(to.getCreated_at())
            .updated_at(to.getUpdated_at())
            .flag(to.getFlag())
            .wikiDataId(to.getWikiDataId())
            .build();
}






}
