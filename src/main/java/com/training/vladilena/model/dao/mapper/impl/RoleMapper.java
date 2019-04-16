package com.training.vladilena.model.dao.mapper.impl;

import com.training.vladilena.model.dao.mapper.ObjectMapper;
import com.training.vladilena.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
/**
 * The {@code RoleMapper} implements {@link ObjectMapper}
 *
 * @author Vladlena Ushakova
 */
public class RoleMapper implements ObjectMapper<Role> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Role parseFromResultSet(ResultSet rs) throws SQLException {
        Role result = Role.valueOf(rs.getString("name"));
        result.setId(rs.getLong("role_id"));
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Role makeUnique(Map<Long, Role> roles, Role role) {
        roles.putIfAbsent(role.getId(), role);
        return roles.get(role.getId());
    }
}


