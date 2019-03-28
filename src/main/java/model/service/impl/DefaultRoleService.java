package model.service.impl;

import model.dao.DaoFactory;
import model.dao.RoleDao;
import model.entity.Role;
import model.service.RoleService;

import java.util.List;


public class DefaultRoleService implements RoleService {
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
                    //  logger.debug("Create first DefaultLoginService instance");
                }
            }
        }
        //logger.debug("Return DefaultLoginService instance");
        return roleService;
    }



    @Override
    public List<Role> getAll() {
        return roleDAO.findAll();
    }
}


