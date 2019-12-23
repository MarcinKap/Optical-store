//package com.opticalstore.validators;
//
//
//import com.opticalstore.security.UserApp;
//import org.springframework.validation.Errors;
//import org.springframework.validation.ValidationUtils;
//import org.springframework.validation.Validator;
//import utilities.AppUtils;
//
//public class UserRegisterValidator implements Validator {
//
//    @Override
//    public boolean supports(Class<?> cls) {
//        return UserApp.class.equals(cls);
//    }
//
//    @Override
//    public void validate(Object obj, Errors errors) {
//        UserApp u = (UserApp) obj;
//
//        ValidationUtils.rejectIfEmpty(errors, "name", "error.userName.empty");
//        ValidationUtils.rejectIfEmpty(errors, "lastname", "error.userLastName.empty");
//        ValidationUtils.rejectIfEmpty(errors, "email", "error.userEmail.empty");
//        ValidationUtils.rejectIfEmpty(errors, "password", "error.userPassword.empty");
//
//        if (!u.getEmail().equals(null)) {
//            boolean isMatch = AppUtils.checkEmail(u.getEmail());
//            if (!isMatch) {
//                errors.rejectValue("email", "error.userEmailIsNotMatch");
//            }
//        }
//        if (!u.getPassword().equals(null)) {
//            boolean isMatch = AppUtils.checkPassword(u.getPassword());
//            if (!isMatch) {
//                errors.rejectValue("password", "error.userPasswordIsNotMatch");
//            }
//        }
//
////    dokończyć
//    }
//
//
//}
//
