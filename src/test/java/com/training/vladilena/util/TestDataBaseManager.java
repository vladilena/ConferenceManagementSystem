package com.training.vladilena.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestDataBaseManager {
    private static final String DB_CATALOG = "conferences_db_test";
    private static final String DDL_SQL_FILE_NAME = "C:\\Users\\UVlad\\IdeaProjects\\ConferenceManagementSystem\\src\\test\\java\\com\\training\\vladilena\\resources\\conferences_db_test_DDL";
    private static final String DML_SQL_FILE_NAME = "C:\\Users\\UVlad\\IdeaProjects\\ConferenceManagementSystem\\src\\test\\java\\com\\training\\vladilena\\resources\\conferences_db_test_DML";

    public static void setUpTestDDL() {
        try (Connection conn = new TestConnectionPoolHolder().getConnection();
             Statement stm = conn.createStatement()) {
            stm.execute("DROP DATABASE IF EXISTS " + DB_CATALOG);

            Path path = Paths.get(DDL_SQL_FILE_NAME);
            List<String> sqlList = Files.readAllLines(path);
            for (String sql : sqlList) {
                stm.addBatch(sql);
            }

            stm.executeBatch();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void setUpTestDML() {
        try (Connection conn = new TestConnectionPoolHolder().getConnection();
             Statement stm = conn.createStatement()) {


            stm.addBatch("DELETE FROM " + DB_CATALOG + ".speaker");
            stm.addBatch("DELETE FROM " + DB_CATALOG + ".user");
            stm.addBatch("DELETE FROM " + DB_CATALOG + ".role");
            stm.addBatch("DELETE FROM " + DB_CATALOG + ".conference");
            stm.addBatch("DELETE FROM " + DB_CATALOG + ".lecture");
            stm.addBatch("DELETE FROM " + DB_CATALOG + ".user_conference");


            Path path = Paths.get(DML_SQL_FILE_NAME);
            List<String> sqlList = Files.readAllLines(path);
            for (String sql : sqlList) {
                stm.addBatch(sql);
            }

            stm.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

