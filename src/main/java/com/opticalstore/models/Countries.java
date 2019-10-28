package com.opticalstore.models;


import lombok.*;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "countries")
public class Countries {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
