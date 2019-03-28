package model.dao.impl;


import model.util.ConnectionDBManager;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionPoolHolder {
    private static BasicDataSource ds = new BasicDataSource();

    static {
        ds.setUrl(ConnectionDBManager.getProperty("url"));
        ds.setUsername(ConnectionDBManager.getProperty("user"));
        ds.setPassword(ConnectionDBManager.getProperty("pass"));
        ds.setMinIdle(5);
        ds.setMaxIdle(10);
        ds.setMaxOpenPreparedStatements(100);
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    private ConnectionPoolHolder(){ }
}


