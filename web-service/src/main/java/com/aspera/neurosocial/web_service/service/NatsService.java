package com.aspera.neurosocial.web_service.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Message;
import io.nats.client.Nats;
import io.nats.client.Subscription;


@Service
public class NatsService {

    private Connection connection;

    private String natsUrl = "nats://localhost:4222";

    public NatsService() throws IOException, InterruptedException {
        connection = Nats.connect(natsUrl);
    }

    public NatsService(Connection connection) {
        this.connection = connection;
    }

    public void sendMessage(String subject, String message) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        connection.publish(subject, messageBytes);
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
