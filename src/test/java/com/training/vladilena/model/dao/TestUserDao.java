package com.training.vladilena.model.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.training.vladilena.model.dao.data.*;
import com.training.vladilena.model.dao.impl.MySQLUserDao;
import com.training.vladilena.model.entity.*;
import com.training.vladilena.util.TestConnectionPoolHolder;
import com.training.vladilena.util.TestDataBaseManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class TestUserDao {

    private static UserDao userDao;


    @BeforeClass
    public static void setUpClass() {
        TestDataBaseManager.setUpTestDDL();
        TestDataBaseManager.setUpTestDML();
        try {
            userDao = new MySQLUserDao(new TestConnectionPoolHolder().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findAllTest() throws SQLException {
        List<User> expectedUsers = new ArrayList<>();
        expectedUsers.add(UserBuilder.getBuilder().constructUser(1L,
                RoleBuilder.getBuilder().constructRole(Role.MODERATOR)).build());
        expectedUsers.add(UserBuilder.getBuilder().constructUser(2L,
                RoleBuilder.getBuilder().constructRole(Role.SPEAKER)).build());
        expectedUsers.add(UserBuilder.getBuilder().constructUser(3L,
                RoleBuilder.getBuilder().constructRole(Role.USER)).build());

        List<User> actualUsers = userDao.findAll();

        assertEquals(expectedUsers, actualUsers);
    }


    @Test
    public void findByIdTest() {
        User expectedUser = UserBuilder.getBuilder().constructUser(1L,
                RoleBuilder.getBuilder().constructRole(Role.MODERATOR)).build();

        User actualUser = userDao.findById(expectedUser.getId());

        assertEquals(expectedUser, actualUser);
    }


    @Test
    public void findByLoginTest() {
        User expectedUser = UserBuilder.getBuilder().constructUser(1L,
                RoleBuilder.getBuilder().constructRole(Role.MODERATOR)).build();

        User actualUser = userDao.findByLogin(expectedUser.getLogin());

        assertEquals(expectedUser, actualUser);
    }


    @Test
    public void updateTest() {
        User user = UserBuilder.getBuilder().constructUser(1L,
                RoleBuilder.getBuilder().constructRole(Role.SPEAKER)).build();
        user.setFirstName("update");

        assertTrue(userDao.update(user));
        assertEquals(user, userDao.findById(user.getId()));
        TestDataBaseManager.setUpTestDML();
    }


    @Test
    public void createTest() {
        User user = UserBuilder.getBuilder().constructUser(4L,
                RoleBuilder.getBuilder().constructRole(Role.USER)).build();
        assertTrue(userDao.create(user));
        assertEquals(user, userDao.findByLogin(user.getLogin()));
        TestDataBaseManager.setUpTestDML();
    }


    @Test
    public void deleteTest() {
        User user = UserBuilder.getBuilder().constructUser(1L,
                RoleBuilder.getBuilder().constructRole(Role.MODERATOR)).build();

        assertTrue(userDao.delete(user.getId()));
        assertNull(userDao.findById(user.getId()));
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    public void getAllLoginsTest() {
        User user1 = UserBuilder.getBuilder().constructUser(1L,
                RoleBuilder.getBuilder().constructRole(Role.USER)).build();
        User user2 = UserBuilder.getBuilder().constructUser(2L,
                RoleBuilder.getBuilder().constructRole(Role.USER)).build();
        User user3 = UserBuilder.getBuilder().constructUser(3L,
                RoleBuilder.getBuilder().constructRole(Role.USER)).build();
        Set<String> planned = new HashSet<>();
        planned.add(user3.getLogin());
        planned.add(user2.getLogin());
        planned.add(user1.getLogin());

        assertEquals(planned, userDao.getAllLogins());
    }


    @Test
    public void findIdByLoginTest() {
        User expectedUser = UserBuilder.getBuilder().constructUser(5L,
                RoleBuilder.getBuilder().constructRole(Role.USER)).build();
        long expectedId = expectedUser.getId();
        userDao.create(expectedUser);
        long actualId = userDao.findIdByLogin(expectedUser.getLogin());

        assertEquals(expectedId, actualId);
        TestDataBaseManager.setUpTestDML();
    }

    @Test
    public void subscribeOnConferenceTest() {
        User user = UserBuilder.getBuilder().constructUser(2L,
                RoleBuilder.getBuilder().constructRole(Role.USER)).build();
        Conference conference = ConferenceBuilder.getBuilder().constructConference(1L).build();
        assertTrue(userDao.subscribeOnConference(user.getId(), conference.getId()));
        TestDataBaseManager.setUpTestDML();
    }

}



