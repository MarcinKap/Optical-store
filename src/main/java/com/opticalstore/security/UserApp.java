package com.opticalstore.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opticalstore.models.Adresses;
import com.opticalstore.models.CompaniesAdresses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

//MODEL

@Data
//@Getter
//@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user", schema = "public")
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "user_id")
    private Integer id;
//    @NotNull
    private String email;
//    @NotNull
    private String password;
//    @NotNull
    private String name;
////    @NotNull
    private String lastName;
    private int active;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userApp")
    private Set<Adresses> adresses = new HashSet<>();

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userApp")
    private Set<CompaniesAdresses> companies_adresses = new HashSet<>();


    public UserApp(UserApp userApp) {
        this.id=userApp.getId();
//        this.username = userApp.getUsername();
        this.email = userApp.getEmail();
        this.password = userApp.getPassword();
        this.active = userApp.getActive();


        this.roles = userApp.getRoles();
        this.adresses = userApp.getAdresses();
        this.companies_adresses = userApp.getCompanies_adresses();
    }

//    public UserApp(UserApp userApp) {
//        this.email = email;
//        this.password = password;
////        this.name = name;
////        this.lastName = lastName;
//        this.active = active;
//        this.roles = roles;
//        this.adresses = adresses;
//        this.companies_adresses = companies_adresses;
//    }


}
