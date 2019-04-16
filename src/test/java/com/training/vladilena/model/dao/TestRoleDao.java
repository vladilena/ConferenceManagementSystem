package com.training.vladilena.model.dao;

import com.training.vladilena.model.dao.data.RoleBuilder;
import com.training.vladilena.model.dao.impl.MySQLRoleDao;
import com.training.vladilena.model.entity.Role;
import com.training.vladilena.util.TestConnectionPoolHolder;
import com.training.vladilena.util.TestDataBaseManager;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class TestRoleDao {
    private static RoleDao roleDao;


    @BeforeClass
    public static void setUpClass() {
        TestDataBaseManager.setUpTestDDL();
        TestDataBaseManager.setUpTestDML();
        try {
            roleDao = new MySQLRoleDao(new TestConnectionPoolHolder().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void findAllTest() throws SQLException {
        List<Role> expectedRoles = new ArrayList<>();
        expectedRoles.add(RoleBuilder.getBuilder().constructRole(Role.MODERATOR).build());
        expectedRoles.add(RoleBuilder.getBuilder().constructRole(Role.SPEAKER).build());
        expectedRoles.add(RoleBuilder.getBuilder().constructRole(Role.USER).build());

        List<Role> actualRoles = roleDao.findAll();

        assertEquals(expectedRoles, actualRoles);
    }



    @Test
    public void findByNameTest() {
        Role expectedRole = RoleBuilder.getBuilder().constructRole(Role.MODERATOR).build();

        Role actualRole = roleDao.findByName(Role.MODERATOR.name());

        assertEquals(expectedRole, actualRole);
    }



    @Test
    public void findByIdTest() {
        Role expectedRole = RoleBuilder.getBuilder().constructRole(Role.MODERATOR).build();
        Role actualRole = roleDao.findById(1);

        assertEquals(expectedRole, actualRole);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void createTest(){
        Role role = RoleBuilder.getBuilder().constructRole(Role.USER).build();
        roleDao.create(role);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void updateTest(){
        Role role = RoleBuilder.getBuilder().constructRole(Role.USER).build();
        roleDao.update(role);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void deleteTest(){
        Role role = RoleBuilder.getBuilder().constructRole(Role.USER).build();
        roleDao.delete(role.getId());
    }
}


