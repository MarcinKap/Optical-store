package com.opticalstore.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class CustomUserService implements UserDetailsService {


    private UserAppRepository userAppRepository;
    private PasswordEncoder passwordEncoder;

    public CustomUserService(UserAppRepository userAppRepository, PasswordEncoder passwordEncoder) {
        this.userAppRepository = userAppRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAppRepository
                .findUserAppByName(username)
                .map(CustomUserDetails::new)
                .orElseThrow( () -> new UsernameNotFoundException("User not find!"));
    }

    public void saveUserApp(LoginUser loginUser) {

        //TODO uzupelnic przypisywanie ról do użytkowników.

//        Set<String> roles =  userApp.getRoles()
//                .stream()
//                .map(RolesToStringsSet.INSTANCE)
//                .collect(Collectors.toSet());

//        Set<Role> roles = userApp.getRoles()
//                .stream()
//                .map()
//

//
//        Role role = Role
//                .builder()
//                .role("1")
//                .roleId(6)
//                .users()
//                .build();

        UserApp result = UserApp
                .builder()
                .username(loginUser.getUsername())
                .password(passwordEncoder.encode(loginUser.getPassword())) //haslo  w stanie nieczytelnym
                .active(1)
                .roles(new HashSet<>())
                .build();
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

    private enum RolesToStringsSet implements Function<Role, String> {
        INSTANCE;

        @Override
        public String apply(Role role)
        {
            return role.getRole();
        }
    }


}


