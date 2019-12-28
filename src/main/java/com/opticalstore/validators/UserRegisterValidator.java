package com.opticalstore.validators;


import com.opticalstore.security.LoginUser;
import com.opticalstore.security.UserApp;
import org.springframework.context.MessageSource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import utilities.AppUtils;

import java.util.Locale;


public class UserRegisterValidator implements Validator {


    @Override
    public boolean supports(Class<?> cls) {
        return UserApp.class.equals(cls);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        LoginUser u = (LoginUser) obj;

        ValidationUtils.rejectIfEmpty(errors, "name", "error.userName.empty");
        ValidationUtils.rejectIfEmpty(errors, "lastName", "error.userLastName.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "error.userEmail.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "error.userPassword.empty");

        if (!u.getEmail().equals(null)) {
            boolean isMatch = AppUtils.checkEmail(u.getEmail());
            if (!isMatch) {
                errors.rejectValue("email", "error.userEmailIsNotMatch");
            }
        }
        if (!u.getPassword().equals(null)) {
            boolean isMatch = AppUtils.checkPassword(u.getPassword());
            if (!isMatch) {
                errors.rejectValue("password", "error.userPasswordIsNotMatch");
            }
        }
    }


    public void validateEmailExist(UserApp userApp, Errors errors) {

        if (userApp != null) {
            errors.rejectValue("email", "error.userEmailExist");
        }

    }



}

