package model.service.impl;

import model.dao.DaoFactory;
import model.dao.RoleDao;
import model.entity.Role;
import model.service.RoleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


public class DefaultRoleService implements RoleService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultRoleService.class);

    private static volatile RoleService roleService;
    private static RoleDao roleDAO;

    private DefaultRoleService() {
        roleDAO = DaoFactory.getInstance().getRoleDao();
    }

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


