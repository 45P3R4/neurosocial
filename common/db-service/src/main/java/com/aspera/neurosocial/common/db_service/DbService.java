package com.aspera.neurosocial.common.db_service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

public class DbService {

    @Getter
    private Connection connection;

    private final String connectionString = "jdbc:postgresql://localhost:5432/social";
    private final String user = "social_admin";
    private final String password = "admin";

    private final Logger logger = LoggerFactory.getLogger(DbService.class);

    public DbService() {
        connectToDb();
    }

    public void connectToDb() {
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
