package model.dao.mapper;

import model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RoleMapper implements ObjectMapper<Role> {
    @Override
    public Role parseFromResultSet(ResultSet rs) throws SQLException {
            Role result = Role.valueOf(rs.getString("name"));
            result.setId(rs.getLong("role_id"));
            return result;
    }

    @Override
    public Role makeUnique(Map<Long, Role> roles, Role role) {
            roles.putIfAbsent(role.getId(), role);
            return roles.get(role.getId());
    }
}


