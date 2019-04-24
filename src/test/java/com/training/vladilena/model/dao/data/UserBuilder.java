package com.training.vladilena.model.dao.data;

import com.training.vladilena.model.entity.Role;
import com.training.vladilena.model.entity.User;

public class UserBuilder implements Builder<User> {
    private User user;

    private UserBuilder() {
        user = new User();
    }

    public static UserBuilder getBuilder() {
        return new UserBuilder();
    }

    public UserBuilder constructUser(Long template, Builder<Role> roleBuilder) {
        if (template != null) {
            user.setId(template);
            user.setLogin("login" + template);
            user.setPassword("password" + template);
            user.setEmail("email@ukr.net" + template);
            user.setFirstName("text");
            user.setFirstNameEn("text");
            user.setLastName("text");
            user.setLastNameEn("text");
        }
        user.setRole(roleBuilder != null ? roleBuilder.build() : null);

        return this;
    }

    public UserBuilder constructUser(Long template) {
        if (template != null) {
            user.setId(template);
            user.setLogin("login" + template);
            user.setPassword("password" + template);
            user.setEmail("email@ukr.net" + template);
            user.setFirstName("text");
            user.setFirstNameEn("text");
            user.setLastName("text");
            user.setLastNameEn("text");
        }

        return this;
    }

    @Override
    public User build() {
        return user;
    }
}
