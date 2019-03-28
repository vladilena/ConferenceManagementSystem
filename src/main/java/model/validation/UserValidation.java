package model.validation;

public interface UserValidation {
    boolean loginValid(String login);
    boolean emailValid(String email);
    boolean passwordValid(String password);
    boolean firstNameValid(String firstName);
    boolean lastNameValid(String lastName);
    boolean firstNameEnValid(String firstNameEn);
    boolean lastNameEnValid(String lastNameEn);

}
