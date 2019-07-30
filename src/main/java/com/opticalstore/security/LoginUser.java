package com.opticalstore.security;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//MODEL

@Getter
@Setter
@Data
public class LoginUser {

    private String username;
    private String password;
}
