package model.dao.impl;

import model.dao.RoleDao;
import model.dao.mapper.RoleMapper;
import model.entity.Role;
import model.util.SQLManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLRoleDao implements RoleDao {
    private Connection connection;
    private RoleMapper roleMapper;

    MySQLRoleDao(Connection connection) {
        this.connection = connection;
        roleMapper = new RoleMapper();
    }


    @Override
    public boolean create(Role entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Role findById(long id) {
        Role role = null;
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("find.role.by.id"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                role = roleMapper.parseFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    @Override
    public List<Role> findAll() {
        List<Role> result = new ArrayList<>();
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("find.all.roles"));
            while (rs.next()) {
                result.add(roleMapper.parseFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean update(Role entity) {
       throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Role findByName(String name) {
        Role role = null;
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("find.role.by.name"))) {
            stm.setString(1, name);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                role = roleMapper.parseFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}


