package com.training.vladilena.model.dao.impl;


import com.training.vladilena.model.dao.ConnectionPoolHolder;
import com.training.vladilena.util.ConnectionDBManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;


public class MySQLConnectionPoolHolder implements ConnectionPoolHolder {
    private static final Logger LOGGER = LogManager.getLogger(MySQLConnectionPoolHolder.class);
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(ConnectionDBManager.getProperty("url"));
        ds.setUsername(ConnectionDBManager.getProperty("user"));
        ds.setPassword(ConnectionDBManager.getProperty("pass"));
        ds.setMinIdle(Integer.valueOf(ConnectionDBManager.getProperty("min.idle")));
        ds.setMaxIdle(Integer.valueOf(ConnectionDBManager.getProperty("max.idle")));
        ds.setMaxOpenPreparedStatements(Integer.valueOf(ConnectionDBManager.getProperty("max.open.prepare.statement")));
    }

    public static Connection getConnection() throws SQLException {
        LOGGER.debug("Return connection to DB with connection pool");
        return ds.getConnection();
    }

    private MySQLConnectionPoolHolder() {
    }
}


