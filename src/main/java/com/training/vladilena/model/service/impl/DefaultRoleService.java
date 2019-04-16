package com.training.vladilena.model.service.impl;

import com.training.vladilena.model.dao.DaoFactory;
import com.training.vladilena.model.dao.RoleDao;
import com.training.vladilena.model.entity.Role;
import com.training.vladilena.model.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * {@inheritDoc}
 */
public class DefaultRoleService implements RoleService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultRoleService.class);

    private static volatile RoleService roleService;
    private static RoleDao roleDAO;

    private DefaultRoleService() {
        roleDAO = DaoFactory.getInstance().getRoleDao();
    }
    /**
     * Always return same {@link DefaultRoleService} instance
     *
     * @return always return same {@link DefaultRoleService} instance
     */
    public static RoleService getInstance(){
        RoleService localInstance = roleService;
        if(localInstance == null) {
            synchronized (DefaultLoginService.class) {
                localInstance = roleService;
                if(localInstance == null) {
                    roleService = new DefaultRoleService();
                      LOGGER.debug("Create first DefaultRoleService instance");
                }
            }
        }
        LOGGER.debug("Return DefaultRoleService instance");
        return roleService;
    }

    @Override
    public List<Role> getAll() {
        return roleDAO.findAll();
    }
}


