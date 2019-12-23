package com.opticalstore.security;

import com.opticalstore.models.Adresses;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

//MODEL

@Getter
@Setter
@Data
public class LoginUser {
    private String email;
    private String password;
    private String name;
    private String lastName;
}
