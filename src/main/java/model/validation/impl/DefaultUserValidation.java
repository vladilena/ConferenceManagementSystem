package model.validation.impl;


import model.util.RegexManager;
import model.validation.UserValidation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultUserValidation implements UserValidation {
    private static volatile UserValidation userValidation;

    private DefaultUserValidation() {
    }

    public static UserValidation getInstance(){
        UserValidation localInstance = userValidation;
        if(localInstance == null) {
            synchronized (DefaultUserValidation.class) {
                localInstance = userValidation;
                if(localInstance == null) {
                    userValidation = new DefaultUserValidation();
                   // logger.debug("Create first DefaultUserValidation instance");
                }
            }
        }
       // logger.debug("Return DefaultUserValidation instance");
        return userValidation;
    }


    @Override
    public boolean loginValid(String login) {
        if (login == null){return false;}
        return Pattern.matches(RegexManager.getProperty("login"), login);
    }

    @Override
    public boolean emailValid(String email) {
        if (email == null){return false;}
        return Pattern.matches(RegexManager.getProperty("email"), email);
    }

    @Override
    public boolean passwordValid(String password) {
        if (password == null){return false;}
        return Pattern.matches(RegexManager.getProperty("password"), password);
    }

    @Override
    public boolean firstNameValid(String firstName) {
        if (firstName == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("name.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(firstName);
        return (m.matches());
    }

    @Override
    public boolean lastNameValid(String lastName) {
        if (lastName == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("name.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(lastName);
        return (m.matches());
    }

    @Override
    public boolean firstNameEnValid(String firstNameEn) {
        if (firstNameEn == null){return false;}
        return Pattern.matches(RegexManager.getProperty("name.en"), firstNameEn);
    }

    @Override
    public boolean lastNameEnValid(String lastNameEn) {
        if (lastNameEn == null){return false;}
        return Pattern.matches(RegexManager.getProperty("name.en"), lastNameEn);
    }
}


