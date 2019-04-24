package com.training.vladilena.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The {@code ConnectionPoolHolder} is an interface to get a connection to a database
 * from connection pool
 *
 * @author Vladlena Ushakova
 */
public interface ConnectionPoolHolder {
    static Connection getConnection() throws SQLException {
        return null;
    }
}
