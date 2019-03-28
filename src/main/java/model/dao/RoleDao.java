package model.dao;

import model.entity.Role;

public interface RoleDao extends GenericDao<Role> {
    Role findByName(String name);
}
