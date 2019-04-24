package com.training.vladilena.model.validation;

import com.training.vladilena.model.entity.User;

/**
 * The {@code UserValidation} is a interface which provide method to validate {@link User}
 *
 * @author Vladlena ushakova
 */
public interface UserValidation {
    boolean loginValid(String login);

    boolean emailValid(String email);

    boolean passwordValid(String password);

    boolean firstNameValid(String firstName);

    boolean lastNameValid(String lastName);

    boolean firstNameEnValid(String firstNameEn);

    boolean lastNameEnValid(String lastNameEn);

}
