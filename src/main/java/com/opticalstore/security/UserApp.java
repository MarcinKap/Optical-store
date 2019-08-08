package com.opticalstore.security;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
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
    private String username;
    private String password;
    private int active;

    //nowe




    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns =
            @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();
    public UserApp(UserApp userApp) {
        this.username = userApp.getUsername();
        this.password = userApp.getPassword();
        this.active = userApp.getActive();
        this.roles = userApp.getRoles();
    }
}
