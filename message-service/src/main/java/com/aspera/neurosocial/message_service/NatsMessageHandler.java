package com.aspera.neurosocial.message_service;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.aspera.neurosocial.dto.AiResponseDto;
import com.aspera.neurosocial.message_service.service.DbManager;
import com.aspera.neurosocial.message_service.service.NatsService;

import jakarta.annotation.PostConstruct;
import tools.jackson.databind.ObjectMapper;

@Service
public class NatsMessageHandler {

    public DbManager db;
    private NatsService nats;
    private final ObjectMapper mapper;

    private String dbAddress = "jdbc:postgresql://localhost:5432/social";
    private String dbUser = "social_admin";
    private String dbPassword = "admin";

    private PreparedStatement createStatement;
    // private PreparedStatement readStatement;
    // private PreparedStatement updateStatement;
    // private PreparedStatement deleteStatement;

    public NatsMessageHandler(ObjectMapper mapper) throws SQLException, IOException, InterruptedException {
        this.nats = new NatsService();
        this.mapper = mapper;
        this.db = new DbManager();

        db.connectToDb(dbAddress, dbUser, dbPassword);

        createStatement = db.getConnection().prepareStatement(
                "insert into messages (parent_id, author_id, text, rating, timestamp) values (?, ?, ?, ?, ?);");
        // readStatement = db.getConnection().prepareStatement("select * from messages where id = ?");
        // updateStatement = db.getConnection().prepareStatement(
        //         "update messages set parent_id = ?, author_id = ?, text = ?, rating = ?, timestamp = ? where id = ?");
        // deleteStatement = db.getConnection().prepareStatement("delete from messages where id = ?");
    }

    @PostConstruct
    public void init() {
        HandleMessage();
    }

    public void HandleMessage() {

        nats.setupSubscription("sending-message", (msg) -> {
            AiResponseDto aiResponse = mapper.readValue(msg.getData(), AiResponseDto.class);
            System.err.println("\n" + aiResponse);

            long parent_id = 0;
            long author_id = 0;
            String text = aiResponse.getText();
            int rating = 0;
            LocalDateTime timestamp = LocalDateTime.now();

            try {
                createStatement.setLong(1, parent_id);
                createStatement.setLong(2, author_id);
                createStatement.setString(3, text);
                createStatement.setInt(4, rating);
                createStatement.setTimestamp(5, Timestamp.valueOf(timestamp));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                createStatement.executeUpdate();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            // createStatement.setLong(2, author_id);
            // createStatement.setString(3, text);
            // createStatement.setInt(4, rating);
            // createStatement.setTimestamp(5, Timestamp.valueOf(timestamp));

        });
    }

}
