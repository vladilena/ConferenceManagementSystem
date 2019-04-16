package com.training.vladilena.model.dao.impl;

import com.training.vladilena.model.dao.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
/**
 * The {@code MySQLDaoFactory} class extends {@link DaoFactory} and returns instances of DAO
 *
 * @author Vladlena Ushakova
 */
public class MySQLDaoFactory extends DaoFactory {
    private static final Logger LOGGER = LogManager.getLogger(MySQLDaoFactory.class);


    @Override
    public ConferenceDao getConferenceDao() {
        LOGGER.debug("Return instance of ConferenceDao");
        return new MySQLConferenceDao(getConnection());
    }

    @Override
    public LectureDao getLectureDao() {
        LOGGER.debug("Return instance of LectureDao");
        return new MySQLLectureDao(getConnection());
    }

    @Override
    public RoleDao getRoleDao() {
        LOGGER.debug("Return instance of RoleDao");
        return new MySQLRoleDao(getConnection());
    }

    @Override
    public UserDao getUserDao() {
        LOGGER.debug("Return instance of UserDao");
        return new MySQLUserDao(getConnection());
    }

    @Override
    public SpeakerDao getSpeakerDao() {
        LOGGER.debug("Return instance of SpeakerDao");
        return new MySQLSpeakerDao(getConnection());
    }

    private Connection getConnection(){
        LOGGER.debug("Try to get connection from connection pool");
        try {
            return MySQLConnectionPoolHolder.getConnection();
        }catch (SQLException e){
            LOGGER.debug("Threw a SQLException"+ e);
            throw new RuntimeException(e);
        }
    }
}



