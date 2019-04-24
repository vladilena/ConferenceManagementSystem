package com.training.vladilena.model.dao.mapper.impl;

import com.training.vladilena.model.dao.mapper.ObjectMapper;
import com.training.vladilena.model.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * The {@code RoleMapper} implements {@link ObjectMapper}
 *
 * @author Vladlena Ushakova
 */
public class RoleMapper implements ObjectMapper<Role> {
    private static final Logger LOGGER = LogManager.getLogger(RoleMapper.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public Role parseFromResultSet(ResultSet rs, boolean isLazy) throws SQLException {
        Role result = Role.valueOf(rs.getString("name"));
        result.setId(rs.getLong("role_id"));
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role makeUnique(Map<Long, Role> roles, Role role) {
        LOGGER.debug("Try to parse from role without relations");
        roles.putIfAbsent(role.getId(), role);
        return roles.get(role.getId());
    }

    @Override
    public boolean isEmptyInResultSet(ResultSet rs) throws SQLException {
        LOGGER.debug("Try to find out if role is empty in result set");
        return rs.getLong("role_id") == 0;
    }
}


