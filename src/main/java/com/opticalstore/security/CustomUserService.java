package com.opticalstore.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
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
                .orElseThrow(() -> new UsernameNotFoundException("Email not find!"));
    }

    public UserApp findUserByEmail(String email) {
        return Optional
                .ofNullable(userAppRepository.findUserAppByEmail(email))
                .orElse(null);
    }

    public void saveUserApp(LoginUser loginUser) {
        Role role = roleRepository.findRoleByName("ADMIN");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        UserApp result = UserApp
                .builder()
//                .username(loginUser.getUsername())
                .email(loginUser.getEmail())
                .password(passwordEncoder.encode(loginUser.getPassword()))
                .name(loginUser.getName())
                .lastName(loginUser.getLastName())
                .active(1)
                .roles(roles)
                .build();
        userAppRepository.save(result);
    }

    public void updateUserAppPassword(String newPassword) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserApp userApp = (UserApp) authentication.getPrincipal();
        Optional
                .ofNullable(userAppRepository.findUserAppById(userApp.getId()))
                .map(c -> {
//                        c.setId(currentUser.getId());
//                        c.setEmail(currentUser.getEmail());
                    c.setPassword(passwordEncoder.encode(newPassword));
//                        c.setRoles(currentUser.getRoles());
//                        c.setActive(currentUser.getActive());
//                        c.setAdresses(currentUser.getAdresses());
//                        c.setCompanies_adresses(currentUser.getCompanies_adresses());
//                        c.setLastName(currentUser.getLastName());
//                        c.setName(currentUser.getName());

                    return userAppRepository.save(c);
                })
                .orElse(null);
    }


}












