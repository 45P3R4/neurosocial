package com.aspera.neurosocial.message_service.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

public class DbManager {

    @Getter
    private Connection connection;

    private String dbAddress = "jdbc:postgresql://localhost:5432/social";
    private String dbUser = "social_admin";
    private String dbPassword = "admin";

    private final Logger logger = LoggerFactory.getLogger(DbManager.class);

    public DbManager() throws SQLException {
        connection = DriverManager.getConnection(dbAddress, dbUser, dbPassword);
    }

    public void connectToDb(String connectionString, String user, String password) {

        logger.info("Connecting to " + connectionString + "... ");

        try {
            this.connection = DriverManager.getConnection(connectionString, user, password);
            logger.info("Connection established");
        } catch (SQLException e) {
            logger.error("Data base service error:", e);
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                logger.info("Connection closed");
            } catch (SQLException e) {
                logger.error("Error closing connection:", e);
            }
        }
    }

}
