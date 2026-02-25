package com.aspera.neurosocial.message_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.aspera.neurosocial.common.db_service.DbService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/message")
public class MessageController {

    private DbService db;

    private PreparedStatement createStatement;
    private PreparedStatement readStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;

    public MessageController() throws SQLException {
        db = new DbService();
        createStatement = db.getConnection().prepareStatement(
                "insert into messages (parent_id, author_id, text, rating, timestamp) values (?, ?, ?, ?, ?);");
        readStatement = db.getConnection().prepareStatement("select * from messages where id = ?");
        updateStatement = db.getConnection().prepareStatement(
                "update messages set parent_id = ?, author_id = ?, text = ?, rating = ?, timestamp = ? where id = ?");
        deleteStatement = db.getConnection().prepareStatement("delete from messages where id = ?");
    }

    @GetMapping("create")
    public boolean createMessage(
            @RequestParam long parent_id,
            @RequestParam long author_id,
            @RequestParam String text,
            @RequestParam int rating,
            @RequestParam LocalDateTime timestamp) throws SQLException {

        createStatement.setLong(1, parent_id);
        createStatement.setLong(2, author_id);
        createStatement.setString(3, text);
        createStatement.setInt(4, rating);
        createStatement.setTimestamp(5, Timestamp.valueOf(timestamp));

        int rowsAffected = createStatement.executeUpdate();

        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("read/{id}")
    public String readMessage(@PathVariable long id) throws SQLException {
        readStatement.setLong(1, id);

        try (ResultSet rs = readStatement.executeQuery()) {
            if (rs.next()) {
                return "Message: " + rs.getString("text");
            } else {
                return "Message with id " + id + " not found";
            }
        }
    }

    @GetMapping("update/{id}")
    public boolean updateMessage(
            @PathVariable long id,
            @RequestParam long parent_id,
            @RequestParam long author_id,
            @RequestParam String text,
            @RequestParam int rating,
            @RequestParam LocalDateTime timestamp) throws SQLException {

        updateStatement.setLong(1, parent_id);
        updateStatement.setLong(2, author_id);
        updateStatement.setString(3, text);
        updateStatement.setInt(4, rating);
        updateStatement.setTimestamp(5, Timestamp.valueOf(timestamp));
        updateStatement.setLong(6, id);

        int rowsAffected = updateStatement.executeUpdate();

        if (rowsAffected > 0) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("delete/{id}")
    public boolean deleteMessage(@PathVariable long id) throws SQLException {
        deleteStatement.setLong(1, id);
        deleteStatement.executeUpdate();
        return true;
    }

}
