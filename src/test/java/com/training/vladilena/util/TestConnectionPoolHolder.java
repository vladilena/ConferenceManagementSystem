package com.training.vladilena.util;

import com.training.vladilena.model.dao.ConnectionPoolHolder;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConnectionPoolHolder implements ConnectionPoolHolder {
    private static final Logger LOGGER = LogManager.getLogger(TestConnectionPoolHolder.class);
    private static final String URL = "jdbc:mysql://localhost:3306/conferences_db_test?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final int MIN_IDLE = 5;
    private static final int MAX_IDLE = 10;
    private static final int MAX_OPEN_PREPARE_STATEMENTS = 100;

    public Connection getConnection() throws SQLException {
        try {
            BasicDataSource ds = new BasicDataSource();
            ds.setUrl(URL);
            ds.setUsername(USER);
            ds.setPassword(PASSWORD);
            ds.setMinIdle(MIN_IDLE);
            ds.setMaxIdle(MAX_IDLE);
            ds.setMaxOpenPreparedStatements(MAX_OPEN_PREPARE_STATEMENTS);
            return ds.getConnection();
        } catch (SQLException e) {
            LOGGER.error("SQL Exception: " + e);
        }
        return null;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null)
                con.close();
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:", e);
        }
    }
}
