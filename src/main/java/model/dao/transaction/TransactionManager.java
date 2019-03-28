package model.dao.transaction;


import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void begin() {
        close();

       // connection = //getConnection;

        if(connection != null)
            beginTransaction();
    }

    public boolean commit() {
        if(connection != null)
            return commitTransaction();

        return false;
    }

    public boolean rollback() {
        if(connection != null)
            return rollbackTransaction();

        return false;
    }

    public void close() {
        if(connection != null)
            closeTransaction();
    }

    private void beginTransaction() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            close();
        }
    }

    private boolean commitTransaction() {
        try {
            connection.commit();
            return true;
        } catch (SQLException e) {
            close();
        }
        return false;
    }

    private boolean rollbackTransaction() {
        try {
            connection.rollback();
            return true;
        } catch (SQLException e) {
            close();
        }
        return false;
    }

    private void closeTransaction() {
        try {
            connection.close();
        } catch (SQLException e) {
        }
    }
}
