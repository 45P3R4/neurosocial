package com.aspera.neurosocial.web_service.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.aspera.neurosocial.common.db_service.DbManager;
import com.aspera.neurosocial.web_service.dto.MessageDto;
import com.aspera.neurosocial.web_service.service.NatsService;

@Controller
public class MainController {

	private DbManager db;

	private String dbAddress = "jdbc:postgresql://localhost:5432/social";
	private String dbUser = "social_admin";
	private String dbPassword = "admin";

	private PreparedStatement readStatement;

	public MainController() throws SQLException {
		this.db = new DbManager();
		db.connectToDb(dbAddress, dbUser, dbPassword);
		readStatement = db.getConnection().prepareStatement("select * from messages");
	}

	@GetMapping("/")
	public String home(Model model) throws IOException, InterruptedException, SQLException {
		NatsService nats = new NatsService();

		List<MessageDto> messages = new ArrayList<>();

		try (ResultSet rs = readStatement.executeQuery()) {
            while (rs.next()) {
                // return "Message: " + rs.getString("text");

				MessageDto msg = new MessageDto();
				msg.author = rs.getLong("author_id");
				msg.text = rs.getString("text");
				msg.rating = rs.getInt("rating");
				msg.timestamp = rs.getTimestamp("timestamp").toLocalDateTime();
				messages.add(msg);
            }
        }

		for (MessageDto messageDto : messages) {
			System.err.println("\n\n" + messageDto.text);
		}

		model.addAttribute("messages", messages);

		return "home";

		// model.addAttribute("author", "Meida");
		// model.addAttribute("timestamp", "time");
		// model.addAttribute("message", "Neurosocial");
		// model.addAttribute("rating", "0");

		// return "home";
	}
}
