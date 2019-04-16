package com.training.vladilena.model.dao.data;

import com.training.vladilena.model.entity.Role;

public class RoleBuilder implements Builder<Role> {
    private Role roles;

    private RoleBuilder() {
    }

    public static RoleBuilder getBuilder() {
        return new RoleBuilder();
    }

    public RoleBuilder constructRole(Role role) {
        switch (role) {
            case MODERATOR:
                roles = Role.MODERATOR;
                roles.setId(1L);
                break;
            case SPEAKER:
                roles = Role.SPEAKER;
                roles.setId(2L);
                break;
            case USER:
                roles = Role.USER;
                roles.setId(3L);
        }

        return this;
    }

    @Override
    public Role build() {
        return roles;
    }
}


