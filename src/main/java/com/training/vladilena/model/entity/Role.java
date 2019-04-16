package com.training.vladilena.model.entity;
/**
 * The {@code role} enum represent {@link User} role
 *
 * @author Vladlena Ushakova
 */
public enum Role {
    MODERATOR,
    SPEAKER,
    USER;

    private long id;

    Role() {}

    public void setId(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


}
