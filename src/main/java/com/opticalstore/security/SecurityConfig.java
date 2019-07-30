package com.opticalstore.security;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomUserService customUserService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(CustomUserService customUserService, PasswordEncoder passwordEncoder) {
        this.customUserService = customUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .authorizeRequests()// włączenie autoryzacji zapytań http
                .antMatchers("/login**") //linki ktore biora udział w autoryzacji
                .permitAll() //zezwol na dostep bez autoryzacji na powyzsze linki
                .and() //spojnik pozwalajacy wrocic do konfiguracji obiektu http
                .formLogin() // włączenie opcji mapowania logowania strona html

                .loginPage("/login") //na @GetMapping w LoginController
                //konfiguracja linku pod ktorym aplikacja bedzie odbierac dane logowania
                /** formularz - atrybut w <form action="/signin">*/
                .loginProcessingUrl("/signin")
                // w stronie html pod  /login

                .usernameParameter("username") // parametry formularza
                .passwordParameter("password")
                .successHandler((req, res, auth) -> {
                    //obsługa przypadku poprawnego zalogowania
                    for (GrantedAuthority authority : auth.getAuthorities()) {
                        System.out.println(authority.getAuthority());
                    }
                    System.out.println(auth.getName());
                    res.sendRedirect("/"); //home page url
                })
                .failureHandler((req, res, exp) -> {
                    //obsluga przypadku blednego logowania
                    String errorMessage;
                    if (exp.getClass().isAssignableFrom(BadCredentialsException.class)) {
                        errorMessage = "Invalid username or password";
                    } else {
                        errorMessage = "unknown error: " + exp.getMessage();
                    }

                    req.getSession().setAttribute("message", errorMessage);
                    res.sendRedirect("/login");
                })
                .permitAll()
                .and()

                .logout()
                //włączenie opcji wylogowania
                //link do wylogowania sie
                .logoutUrl("/logout")
                //obsluga skutecznego wylogowania
                .logoutSuccessHandler((req, res, auth) -> {
                    res.sendRedirect("/");
                })
                .permitAll()
                .and()

                // obsluga innych bledow zachowa przy probie logowania
                .exceptionHandling()
                // przechwycenie bledu 403 czyli niedostateczne uprawnienia uzytkownika
                .accessDeniedPage("/login")
                .and()

                // obsluga m in atakow ddos
                .csrf().disable()

                //obsluga filtrowania i akceptacji naglowkow html
                .cors().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(customUserService)
                .passwordEncoder(passwordEncoder);
    }
}

