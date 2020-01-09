package com.opticalstore;

import com.opticalstore.security.LoginUser;
import com.opticalstore.validators.UserRegisterValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import com.opticalstore.utilities.AppUtils;

import javax.validation.Validator;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;


@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class UserRegisterValidationTests {

    Validator validator;
    LoginUser loginUser;
    UserRegisterValidator userRegisterValidator;
    BindingResult results;
    AppUtils appUtils;

    @Before
    public void setUp() {
        loginUser = new LoginUser();
        appUtils = new AppUtils();
    }

    @Test
    public void testValidateMethodWithCorrectInputs() {
        //Correct inputs
        loginUser.setName("Marcin");
        loginUser.setLastName("kap");
        loginUser.setEmail("trolo@o2.pl");
        loginUser.setPassword("Kuba123!@#");
        results = new BeanPropertyBindingResult(loginUser, "loginUser");

        userRegisterValidator = new UserRegisterValidator();
        userRegisterValidator.validate(loginUser, results);
        List<ObjectError> allErrors = results.getAllErrors();
        assertTrue("Errors list size should be null :", results.hasErrors() == false);
    }

    @Test
    public void testValidateMethodWithEmptyName() {
        //Empty Name
        loginUser.setName("");
        loginUser.setLastName("kap");
        loginUser.setEmail("trolo@o2.pl");
        loginUser.setPassword("Kuba123!@#");
        results = new BeanPropertyBindingResult(loginUser, "loginUser");

        userRegisterValidator = new UserRegisterValidator();
        userRegisterValidator.validate(loginUser, results);

        assertTrue("There is not error - empty lastname", results.getFieldError("name").getCode().equals("error.userName.empty"));
    }

    @Test
    public void testValidateMethodWithEmptyLastName() {
        //EmptyLastName
        loginUser.setName("Marcin");
        loginUser.setLastName("");
        loginUser.setEmail("trolo@o2.pl");
        loginUser.setPassword("Kuba123!@#");
        results = new BeanPropertyBindingResult(loginUser, "loginUser");

        userRegisterValidator = new UserRegisterValidator();
        userRegisterValidator.validate(loginUser, results);

        assertTrue("There is not error - empty lastname", results.getFieldError("lastName").getCode().equals("error.userLastName.empty"));
    }

    @Test
    public void testValidateMethodWithEmptyPassword() {
        //EmptyPassword
        loginUser.setName("Marcin");
        loginUser.setLastName("kap");
        loginUser.setEmail("trolo@o2.pl");
        loginUser.setPassword("");
        results = new BeanPropertyBindingResult(loginUser, "loginUser");

        userRegisterValidator = new UserRegisterValidator();
        userRegisterValidator.validate(loginUser, results);

        assertTrue("There is not error - empty password", results.getFieldError("password").getCode().equals("error.userPassword.empty"));
    }

    @Test
    public void testValidateMethodWithEmptyEmail() {
        //Empty Email
        loginUser.setName("Marcin");
        loginUser.setLastName("kap");
        loginUser.setEmail("");
        loginUser.setPassword("Kuba123!@#");
        results = new BeanPropertyBindingResult(loginUser, "loginUser");

        userRegisterValidator = new UserRegisterValidator();
        userRegisterValidator.validate(loginUser, results);

        assertTrue("There is not error - empty email", results.getFieldError("email").getCode().equals("error.userEmail.empty"));
    }

    @Test
    public void TestEmailPattern() {

//        List of Valid Email Addresses
        assertTrue("email@example.com", appUtils.checkEmail("email@example.com"));
        assertTrue("firstname.lastname@example.com", appUtils.checkEmail("firstname.lastname@example.com"));
        assertTrue("email@subdomain.example.com", appUtils.checkEmail("email@subdomain.example.com"));
//        assertTrue("firstname+lastname@example.com", appUtils.checkEmail(" firstname+lastname@example.com"));
//        assertTrue("email@123.123.123.123", appUtils.checkEmail("email@123.123.123.123"));
//        assertTrue("\"email\"@example.com", appUtils.checkEmail("\"email\"@example.com"));
        assertTrue("1234567890@example.com", appUtils.checkEmail("1234567890@example.com"));
//        assertTrue("email@example-one.com", appUtils.checkEmail("email@example-one.com"));
//        assertTrue("_______@example.com", appUtils.checkEmail("_______@example.com"));
        assertTrue("email@example.name", appUtils.checkEmail("email@example.name"));
        assertTrue("email@example.museum", appUtils.checkEmail("email@example.museum"));
        assertTrue("email@example.co.jp", appUtils.checkEmail("email@example.co.jp"));
//        assertTrue("firstname-lastname@example.com", appUtils.checkEmail("firstname-lastname@example.com"));

//        List of Strange Valid Email Addresses
//        assertTrue("much.”more\\ unusual”@example.com", appUtils.checkEmail("much.”more\\ unusual”@example.com"));
//        assertTrue("very.unusual.”@”.unusual.com@example.com", appUtils.checkEmail("very.unusual.”@”.unusual.com@example.com"));
//        assertTrue("very.”(),:;<>[]”.VERY.”very@\\\\\"very”.unusual@strange.example.com", appUtils.checkEmail("very.”(),:;<>[]”.VERY.”very@\\\\\"very”.unusual@strange.example.com"));

//        List of Invalid Email Addresses
        assertFalse("plainaddress", appUtils.checkEmail("plainaddress"));
        assertFalse("#@%^%#$@#$@#.com", appUtils.checkEmail("#@%^%#$@#$@#.com"));
        assertFalse("@example.com", appUtils.checkEmail("@example.com"));
        assertFalse("Joe Smith <email@example.com>", appUtils.checkEmail("Joe Smith <email@example.com>"));
        assertFalse("email.example.com", appUtils.checkEmail("email.example.com"));
        assertFalse("email@example@example.com", appUtils.checkEmail("email@example@example.com"));
        assertFalse(".email@example.com", appUtils.checkEmail(".email@example.com"));
//        assertFalse("email.@example.com", appUtils.checkEmail("email.@example.com"));
//        assertFalse("email..email@example.com", appUtils.checkEmail("email..email@example.com"));
        assertFalse("あいうえお@example.com", appUtils.checkEmail("あいうえお@example.com"));
        assertFalse("email@example.com (Joe Smith)", appUtils.checkEmail("email@example.com (Joe Smith)"));
        assertFalse("email@example", appUtils.checkEmail("email@example"));
        assertFalse("email@-example.com", appUtils.checkEmail("email@-example.com"));
//        assertFalse("email@example.web", appUtils.checkEmail("email@example.web"));
        assertFalse("email@111.222.333.44444", appUtils.checkEmail("email@111.222.333.44444"));
        assertFalse("email@example..com", appUtils.checkEmail("email@example..com"));
//        assertFalse("Abc..123@example.com", appUtils.checkEmail("Abc..123@example.com"));

//        List of Strange Invalid Email Addresses
        assertFalse("”(),:;<>[\\]@example.com", appUtils.checkEmail("”(),:;<>[\\]@example.com"));
        assertFalse("just”not”right@example.com", appUtils.checkEmail("just”not”right@example.com"));
        assertFalse("this\\ is\"really\"not\\allowed@example.com", appUtils.checkEmail("this\\ is\"really\"not\\allowed@example.com"));

    }

    @Test
    public void TestPasswordPattern() {
        Assert.assertTrue(appUtils.checkPassword("Password1!"));
        Assert.assertTrue(appUtils.checkPassword("aPssword1!"));
        Assert.assertTrue(appUtils.checkPassword("#aPssword1!"));

        assertFalse(appUtils.checkPassword(""));
        assertFalse(appUtils.checkPassword("1"));
        assertFalse(appUtils.checkPassword("!"));
        assertFalse(appUtils.checkPassword("P"));
        assertFalse(appUtils.checkPassword("p"));
        assertFalse(appUtils.checkPassword("pass"));
        assertFalse(appUtils.checkPassword("123"));
        assertFalse(appUtils.checkPassword("!@"));
        assertFalse(appUtils.checkPassword("!1"));
        assertFalse(appUtils.checkPassword("11@"));
        assertFalse(appUtils.checkPassword("pP!1"));
        assertFalse(appUtils.checkPassword("Pass word123!@#"));
        assertFalse(appUtils.checkPassword("Password1&"));



    }


}
