package com.aspera.neurosocial.ai_service.service;

import java.io.IOException;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.aspera.neurosocial.dto.AiResponseDto;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;
import tools.jackson.databind.ObjectMapper;


@Service
public class NatsService {

    private Connection connection;
    private ObjectMapper mapper;

    private String natsUrl = "nats://localhost:4222";

    public NatsService() throws IOException, InterruptedException {
        connection = Nats.connect(natsUrl);
        this.mapper = new ObjectMapper();
    }

    public NatsService(Connection connection) {
        this.connection = connection;
    }

    public void sendMessage(String subject, AiResponseDto aiResponse) {
        
        byte[] data = mapper.writeValueAsBytes(aiResponse);
        connection.publish(subject, data);
    }

    public void setupSubscription(String subject, Consumer<Message> messageHandler) {

        Dispatcher dispatcher = connection.createDispatcher((msg) -> {
        });

        Subscription sub = dispatcher.subscribe(subject, (msg) -> {
            messageHandler.accept(msg);
        });

        // sub.unsubscribe();
    }

}
