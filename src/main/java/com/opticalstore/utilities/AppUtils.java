package com.opticalstore.utilities;

import com.opticalstore.constans.UserPatterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    public static boolean checkEmail(String email){
        Pattern p = Pattern.compile(UserPatterns.emailPattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
    public static boolean checkPassword (String password) {
        Pattern p = Pattern.compile(UserPatterns.passwordPattern);
        Matcher m = p.matcher(password);
        return m.matches();
    }


}
