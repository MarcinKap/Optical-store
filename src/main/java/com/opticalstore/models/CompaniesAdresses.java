package com.opticalstore.models;


import com.opticalstore.security.UserApp;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "companies_adresses")
public class CompaniesAdresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private int nipNumber;
    private String city;
    private String zipCode;
    private String street;
    private String apartmentNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_companies_adresses",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_adress_id")
    )
    private UserApp userApp;



}
