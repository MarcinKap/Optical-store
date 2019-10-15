package com.opticalstore.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opticalstore.security.Role;
import com.opticalstore.security.UserApp;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "adresses")
public class Adresses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;


//    private boolean deliveryToTheCompany;
//    private String firmName;
//    private String country;
//    private String town;
//    private String street;
//    private int apartmentNumber;
//    private String zipCode;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "user_adresses",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "adress_id")
    )
    private UserApp userApp123;
//            (mappedBy = "adress")


}
