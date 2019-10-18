package com.opticalstore.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.opticalstore.models.Adresses;
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
    private Integer id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    private int active;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


    //tutaj one to many - adresy
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userApp")
    private Set<Adresses> adresses = new HashSet<>();


    //nowe


//    @NotNull
//    @Size(min = 10, max = 255)
//    private String name;
//
//    @NotNull
//    @Size(min = 10, max = 255)
//    private String surname;

//    @Null
//    @Email
//    private String email;

//    @DateTimeFormat(pattern = "dd/MM/yyyy")
////    @NotNull
//    @Past
//    private Date birthDate;

//    @NotNull
//    @NumberFormat (pattern = "###-###-###")
//    private int telephoneNumber;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
////    @JoinTable(name = "user_adresses",
////            joinColumns =
////            @JoinColumn(name = "user_id"),
////            inverseJoinColumns = @JoinColumn(name = "adress_id")
////    )
//    private Set<Adresses> adress = new HashSet<>();


//    public UserApp(UserApp userApp) {
//        this.username = userApp.getUsername();
//        this.password = userApp.getPassword();
//        this.active = userApp.getActive();
//        this.roles = userApp.getRoles();
//    }


    public UserApp(UserApp userApp) {
        this.id=userApp.getId();
        this.username = userApp.getUsername();
        this.password = userApp.getPassword();
        this.active = userApp.getActive();

        this.roles = userApp.getRoles();
        this.adresses = userApp.getAdresses();
    }




}
