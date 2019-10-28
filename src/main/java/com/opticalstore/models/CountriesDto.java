package com.opticalstore.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountriesDto {
    private int id;
    private String name;
    private String iso3;
    private String iso2;
    private String phonecode;
    private String capital;
    private String currency;
    private Timestamp created_at;
    private Timestamp updated_at;
    private int flag;
    private String wikiDataId;
}
