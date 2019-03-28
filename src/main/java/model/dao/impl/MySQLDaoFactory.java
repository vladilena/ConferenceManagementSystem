package model.dao.impl;

import model.dao.*;
import model.util.ConnectionDBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDaoFactory extends DaoFactory {

    @Override
    public ConferenceDao getConferenceDao() {
        return new MySQLConferenceDao(getConnection());
    }

    @Override
    public LectureDao getLectureDao() {
        return new MySQLLectureDao(getConnection());
    }

    @Override
    public RoleDao getRoleDao() {
        return new MySQLRoleDao(getConnection());
    }

    @Override
    public UserDao getUserDao() {
        return new MySQLUserDao(getConnection());
    }

    @Override
    public SpeakerDao getSpeakerDao() {
        return new MySQLSpeakerDao(getConnection());
    }

    private Connection getConnection(){
        try {
            return ConnectionPoolHolder.getConnection();
//            return DriverManager.getConnection(
//                    ConnectionDBManager.getProperty("url"),
//                    ConnectionDBManager.getProperty("user"),
//                    ConnectionDBManager.getProperty("pass"));
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}



