package com.training.vladilena.model.dao.impl;

import com.training.vladilena.model.dao.RoleDao;
import com.training.vladilena.model.dao.mapper.impl.RoleMapper;
import com.training.vladilena.model.entity.Role;
import com.training.vladilena.util.SQLManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MySQLRoleDao} class implements {@link RoleDao}
 * and specified for MySQL DB
 *
 * @author Vladlena Ushakova
 */
public class MySQLRoleDao implements RoleDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLRoleDao.class);

    private Connection connection;
    private RoleMapper roleMapper;

    public MySQLRoleDao(Connection connection) {
        LOGGER.debug("MySQLRoleDao constructor");
        this.connection = connection;
        roleMapper = new RoleMapper();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean create(Role entity) {
        LOGGER.error("Throw an UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role findById(long id) {
        Role role = null;
        LOGGER.debug("Try to find role by id");

        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("find.role.by.id"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                role = roleMapper.parseFromResultSet(rs, false);
            }
            LOGGER.debug("Select was successful");

        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return role;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Role> findAll() {
        List<Role> result = new ArrayList<>();
        LOGGER.debug("Try to find all roles");
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("find.all.roles"));
            while (rs.next()) {
                result.add(roleMapper.parseFromResultSet(rs, false));
            }
            LOGGER.debug("Found all roles successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Role entity) {
        LOGGER.error("Throw an UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(long id) {
        LOGGER.error("Throw an UnsupportedOperationException");
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() {
        LOGGER.debug("Try to close connection");
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Throw a RuntimeException, full stack trace follows: " + e);
            throw new RuntimeException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role findByName(String name) {
        Role role = null;
        LOGGER.debug("Try to find role by name:" + name);
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("find.role.by.name"))) {
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                role = roleMapper.parseFromResultSet(rs, false);
            }
            LOGGER.debug("Found role successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return role;
    }
}


