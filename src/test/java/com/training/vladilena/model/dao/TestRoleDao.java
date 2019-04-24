package com.training.vladilena.model.dao;

import com.training.vladilena.model.dao.data.RoleBuilder;
import com.training.vladilena.model.dao.impl.MySQLRoleDao;
import com.training.vladilena.model.entity.Role;
import com.training.vladilena.util.TestConnectionPoolHolder;
import com.training.vladilena.util.TestDataBaseManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
 class TestRoleDao {
    private static RoleDao roleDao;

    @BeforeAll
     static void setUpClass() {
        TestDataBaseManager.setUpTestDDL();
        TestDataBaseManager.setUpTestDML();
        try {
            roleDao = new MySQLRoleDao(new TestConnectionPoolHolder().getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
     void findAllTest() throws SQLException {
        List<Role> expectedRoles = new ArrayList<>();
        expectedRoles.add(RoleBuilder.getBuilder().constructRole(Role.MODERATOR).build());
        expectedRoles.add(RoleBuilder.getBuilder().constructRole(Role.SPEAKER).build());
        expectedRoles.add(RoleBuilder.getBuilder().constructRole(Role.USER).build());

        List<Role> actualRoles = roleDao.findAll();

        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    void findByNameTest() {
        Role expectedRole = RoleBuilder.getBuilder().constructRole(Role.MODERATOR).build();

        Role actualRole = roleDao.findByName(Role.MODERATOR.name());

        assertEquals(expectedRole, actualRole);
    }

    @Test
     void findByIdTest() {
        Role expectedRole = RoleBuilder.getBuilder().constructRole(Role.MODERATOR).build();
        Role actualRole = roleDao.findById(1);

        assertEquals(expectedRole, actualRole);
    }

    @Test
    void createTest(){
        Role role = RoleBuilder.getBuilder().constructRole(Role.USER).build();
        assertThrows(UnsupportedOperationException.class, ()->roleDao.create(role));

    }

    @Test
     void updateTest(){
        Role role = RoleBuilder.getBuilder().constructRole(Role.USER).build();
        assertThrows(UnsupportedOperationException.class, ()->roleDao.update(role));
    }

    @Test
     void deleteTest(){
        Role role = RoleBuilder.getBuilder().constructRole(Role.USER).build();
        assertThrows(UnsupportedOperationException.class, ()->roleDao.delete(role.getId()));
    }
}


