package com.training.vladilena.model.transaction;


import com.training.vladilena.model.dao.impl.MySQLConnectionPoolHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger LOGGER = LogManager.getLogger(TransactionManager.class);

    private Connection connection;


    public void begin() throws SQLException {
        connection = MySQLConnectionPoolHolder.getConnection();
        if (connection != null)
            beginTransaction();
    }

    public boolean commit() {
        if (connection != null)
            return commitTransaction();

        return false;
    }

    public boolean rollback() {
        if (connection != null)
            return rollbackTransaction();

        return false;
    }

    public void close() {
        if (connection != null)
            closeTransaction();
    }

    private void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:", e);
            close();
        }
    }

    private boolean commitTransaction() {
        try {
            connection.commit();
            return true;
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:", e);
            close();
        }
        return false;
    }

    private boolean rollbackTransaction() {
        try {
            connection.rollback();
            LOGGER.debug("Statement was roll back");
            return true;
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:", e);
            close();
        }
        return false;
    }

    private void closeTransaction() {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error("Threw a SQLException, full stack trace follows:", e);
        }
    }
}
