package com.opticalstore.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserService implements UserDetailsService {


    private UserAppRepository userAppRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;


    public CustomUserService(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepository
                .findUserAppByName(username)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not find!"));
    }

    public void saveUserApp(LoginUser loginUser) {

        //TODO uzupelnic przypisywanie ról do użytkowników.





        UserApp result = UserApp
                .builder()
                .username(loginUser.getUsername())
                .password(passwordEncoder.encode(loginUser.getPassword())) //haslo  w stanie nieczytelnym
                .active(1)
                .roles(new HashSet<>())
                .build();

//        Set<Role> roles = new HashSet<>();
//
//        roles.add(Role
//                .builder()
//                .role("")
//                .roleId(result.getId())
//                .users()
//                .build());
//
//        result.setRoles(roles);


        userAppRepository.save(result);
    }

//    public void saveUserRole(LoginUser loginUser) {
//
//

//        Role result = Role
//                .builder()
////                .username(loginUser.getUsername())
////                .password(passwordEncoder.encode(loginUser.getPassword())) //haslo  w stanie nieczytelnym
////                .active(1)
////                .roles(new HashSet<>())
//                .build();
//        saveUserRole().save(result);
//    }
//


}


