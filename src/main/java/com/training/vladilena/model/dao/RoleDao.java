package com.training.vladilena.model.dao;

import com.training.vladilena.model.entity.Role;

/**
 * The {@code RoleDao} implements {@link GenericDao}
 * interface for ORM database entity {@link Role}
 *
 * @author Vladlena Ushakova
 */
public interface RoleDao extends GenericDao<Role> {
    /**
     * Method return {@link Role} which find by {@code name}
     *
     * @param name it indicates an {@link Role} {@code name} that you want to return
     * @return return {@link Role} by {@code name}
     */
    Role findByName(String name);
}
