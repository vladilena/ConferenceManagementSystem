package com.training.vladilena.model.dao.mapper.impl;

import com.training.vladilena.model.dao.mapper.ObjectMapper;
import com.training.vladilena.model.entity.Role;
import com.training.vladilena.model.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code UserMapper} implements {@link ObjectMapper}
 *
 * @author Vladlena Ushakova
 */
public class UserMapper implements ObjectMapper<User> {
    private static final Logger LOGGER = LogManager.getLogger(UserMapper.class);
    private static RoleMapper roleMapper = new RoleMapper();
    private Map<Long, Role> roles = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public User parseFromResultSet(ResultSet rs, boolean islazy) throws SQLException {
        User user = null;
        if (islazy) {
            LOGGER.debug("Try to parse from user without relations");
            user = parseWithoutRelations(rs);
        } else {
            LOGGER.debug("Try to parse from user with relations");
            user = parseWithRelations(rs);
        }
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User makeUnique(Map<Long, User> users, User user) {
        LOGGER.info("Try to make user unique");
        users.putIfAbsent(user.getId(), user);
        return users.get(user.getId());
    }

    @Override
    public boolean isEmptyInResultSet(ResultSet rs) throws SQLException {
        LOGGER.debug("Try to find out if user is empty in result set");
        return rs.getLong("user_id") == 0;
    }

    private User parseWithoutRelations(ResultSet rs) throws SQLException {
        User result = new User();
        result.setId(rs.getLong("user_id"));
        result.setLogin(rs.getString("login"));
        result.setPassword(rs.getString("password"));
        result.setEmail(rs.getString("email"));
        result.setFirstName(rs.getString("first_name"));
        result.setFirstNameEn(rs.getString("first_name_en"));
        result.setLastName(rs.getString("last_name"));
        result.setLastNameEn(rs.getString("last_name_en"));
        return result;
    }

    private User parseWithRelations(ResultSet rs) throws SQLException {
        User result = parseWithoutRelations(rs);
        if (!roleMapper.isEmptyInResultSet(rs)) {
            LOGGER.debug("Try to parse from related table(s)");
            Role role = roleMapper.parseFromResultSet(rs, false);
            role = roleMapper.makeUnique(roles, role);
            result.setRole(role);
        } else {
            LOGGER.debug("Related table is empty");
        }
        return result;
    }
}



