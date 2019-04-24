package com.training.vladilena.model.service;

import com.training.vladilena.model.dao.RoleDao;
import com.training.vladilena.model.entity.Role;

import java.util.List;

/**
 * The {@code RoleService} service is a specified API for working with the {@link RoleDao}
 *
 * @author Vladlena Ushakova
 */
public interface RoleService {
    /**
     * Method to get all {@link Role}s
     *
     * @return return {@link List} of all {@link Role}s
     */
    List<Role> getAll();
}
