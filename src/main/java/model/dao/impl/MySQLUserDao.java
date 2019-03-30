package model.dao.impl;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import model.dao.UserDao;
import model.dao.mapper.impl.ConferenceMapper;
import model.dao.mapper.impl.RoleMapper;
import model.dao.mapper.impl.UserMapper;
import model.entity.Conference;
import model.entity.Role;
import model.entity.User;
import model.util.SQLManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public class MySQLUserDao implements UserDao {
    private static final Logger LOGGER = LogManager.getLogger(MySQLUserDao.class);

    private Connection connection;
    private UserMapper userMapper;
    private ConferenceMapper conferenceMapper;
    private RoleMapper roleMapper;

    MySQLUserDao(Connection connection) {
        LOGGER.debug("MySQLUserDao constructor");
        this.connection = connection;
        userMapper = new UserMapper();
        conferenceMapper = new ConferenceMapper();
        roleMapper = new RoleMapper();
    }


    @Override
    public boolean create(User entity) {
        LOGGER.debug("Try to insert into user table");
        boolean resultFlag = false;
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("insert.user"))) {
            setGeneralParamsToPreparedStatement(stm, entity);
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Insert was successful " + entity);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:" + e);
        }
        return resultFlag;
    }

    @Override
    public User findById(long id) {
        User result = null;
        Map<Long, User> users = new HashMap<>();
        Map<Long, Conference> conferences = new HashMap<>();
        Map<Long, Role> roles = new HashMap<>();
        LOGGER.debug("Try to find speaker by id " + id);
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("user.by.id"))) {
            stm.setLong(1, id);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                User user = fillRelatedEntities(rs, users, conferences, roles);
                if (!user.equals(result)) {
                    result = user;
                }
            }
            LOGGER.debug("Select was successful " + result);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return result;
    }

    @Override
    public List<User> findAll() {
        List<User> resultList = new ArrayList<>();
        Map<Long, User> users = new HashMap<>();
        Map<Long, Conference> conferences = new HashMap<>();
        Map<Long, Role> roles = new HashMap<>();
        LOGGER.debug("Try to find all speakers");
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("find.all.users"));
            while (rs.next()) {
                User user = fillRelatedEntities(rs, users, conferences, roles);
                if (!resultList.contains(user)) {
                    resultList.add(user);
                }
            }
            LOGGER.debug("Found all users successful " + resultList);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultList;
    }

    @Override
    public boolean update(User entity) {
        boolean resultFlag = false;
        LOGGER.debug("Try to update user");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("update.user"))) {
            setGeneralParamsToPreparedStatement(stm, entity);
            stm.setLong(9, entity.getId());
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Update was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }


    @Override
    public boolean delete(long id) {
        boolean resultFlag = false;
        LOGGER.debug("Try to delete from user");
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("delete.user"))) {
            stm.setLong(1, id);
            if (stm.executeUpdate() > 0) {
                resultFlag = true;
            }
            LOGGER.debug("Delete was successful");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }

    @Override
    public void close() {
        LOGGER.debug("Try to close connection");
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Threw a RuntimeException, full stack trace follows" + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findByLogin(String login) {
        User result = null;
        Map<Long, User> users = new HashMap<>();
        Map<Long, Role> roles = new HashMap<>();
LOGGER.debug("Try to find user by login: "+login);
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("user.by.login"))) {
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();
           while (rs.next()) {
                User user = userMapper.parseFromResultSet(rs);
                Role role = roleMapper.parseFromResultSet(rs);
                user = userMapper.makeUnique(users, user);
                role = roleMapper.makeUnique(roles, role);
                user.setRole(role);

                if (!user.equals(result)) {
                    result = user;
                }
            }
           LOGGER.debug("Successful find");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return result;
    }

    @Override
    public Set<String> getAllLogins() {
        LOGGER.debug("Try to find all registered logins");
        Set<String> logins = new HashSet<>();
        try (Statement stm = connection.createStatement()) {
            ResultSet rs = stm.executeQuery(SQLManager.getProperty("all.users.logins"));
            while (rs.next()) {
                logins.add(rs.getString("login"));
            }
            LOGGER.debug("Successful found " + logins);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }

        return logins;
    }

    @Override
    public long findIdByLogin(String login) {
        long result = 0;
        LOGGER.debug("Try to find user's id by login "+login);
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("user.id.by.login"))) {
            stm.setString(1, login);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                result = rs.getLong("user_id");
            }
            LOGGER.debug("Successful found");
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }

        return result;
    }

    private void setGeneralParamsToPreparedStatement(PreparedStatement stm, User entity) throws SQLException {
        LOGGER.debug("Try to set all general params to statement");
        stm.setString(1, entity.getLogin());
        stm.setString(2, entity.getPassword());
        stm.setString(3, entity.getEmail());
        stm.setString(4, entity.getFirstName());
        stm.setString(5, entity.getFirstNameEn());
        stm.setString(6, entity.getLastName());
        stm.setString(7, entity.getLastNameEn());
        if(entity.getRole()==Role.USER){
            stm.setLong(8, 3);
        }else if(entity.getRole()==Role.SPEAKER){
            stm.setLong(8, 2);
        }

    }

    private User fillRelatedEntities(ResultSet rs, Map<Long, User> users, Map<Long, Conference> conferences, Map<Long, Role> roles) throws SQLException {
        User user = userMapper.parseFromResultSet(rs);
        Conference conference = conferenceMapper.parseFromResultSet(rs);
        Role role = roleMapper.parseFromResultSet(rs);
        user = userMapper.makeUnique(users, user);
        conference = conferenceMapper.makeUnique(conferences, conference);
        role = roleMapper.makeUnique(roles, role);
        user.getConferences().add(conference);
        user.setRole(role);
        conference.getRegisteredParticipants().add(user);
        return user;
    }

    @Override
    public boolean subscribeOnConference(long userId, long conferenceId) {
        boolean resultFlag = false;
        LOGGER.debug("Try to insert into user_conference table userId:" +userId+"and conferenceId:"+conferenceId);
        try (PreparedStatement stm = connection.prepareStatement(SQLManager.getProperty("subscribe.on.conference"))) {
            stm.setLong(1, userId);
            stm.setLong(2, conferenceId);

            try {
                stm.executeUpdate();
                resultFlag = true;
            } catch (MySQLIntegrityConstraintViolationException e1) {
                LOGGER.error("This user already subscribed on this conference. Threw a MySQLIntegrityConstraintViolationException "+e1);
            }
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows: " + e);
        }
        return resultFlag;
    }
//    public static void main(String[] args) {
//        UserDao userDao = DaoFactory.getInstance().getUserDao();
//        System.out.println(userDao.findAll());
//    }
}



