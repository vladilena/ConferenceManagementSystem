package model.dao.mapper;

import model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User parseFromResultSet(ResultSet rs) throws SQLException {
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

    @Override
    public User makeUnique(Map<Long, User> users, User user) {
        users.putIfAbsent(user.getId(), user);
        return users.get(user.getId());
    }
}



