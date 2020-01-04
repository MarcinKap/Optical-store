package com.opticalstore.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opticalstore.models.Adresses;
import com.opticalstore.models.CompaniesAdresses;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
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
//    @Email
    private String email;
    private String password;
    private String name;
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
        this.email = userApp.getEmail();
        this.password = userApp.getPassword();
        this.active = userApp.getActive();
        this.roles = userApp.getRoles();
        this.adresses = userApp.getAdresses();
        this.companies_adresses = userApp.getCompanies_adresses();
    }
}
