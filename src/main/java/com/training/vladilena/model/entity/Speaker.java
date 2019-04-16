package com.training.vladilena.model.entity;

import java.util.ArrayList;
import java.util.List;
/**
 * The {@code Speaker} class extends {@link User} and expand his rights
 *
 * @author Vladlena Ushakova
 */
public class Speaker extends User {

    private double rating;
    private double bonus;

    private List<Lecture> lectures = new ArrayList<>();

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    public void setLectures(List<Lecture> lectures) {
        this.lectures = lectures;
    }

    @Override
    public String getFirstNameEn() {
        return super.getFirstNameEn();
    }

    @Override
    public void setFirstNameEn(String firstNameEn) {
        super.setFirstNameEn(firstNameEn);
    }

    @Override
    public String getLastNameEn() {
        return super.getLastNameEn();
    }

    @Override
    public void setLastNameEn(String lastNameEn) {
        super.setLastNameEn(lastNameEn);
    }

    @Override
    public long getId() {
        return super.getId();
    }

    @Override
    public void setId(long id) {
        super.setId(id);
    }

    @Override
    public String getLogin() {
        return super.getLogin();
    }

    @Override
    public void setLogin(String login) {
        super.setLogin(login);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return super.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Speaker{" +super.toString()+
                "rating=" + rating +
                ", bonus=" + bonus +
               // ", lectures=" + lectures +
                "} " ;
    }
}


