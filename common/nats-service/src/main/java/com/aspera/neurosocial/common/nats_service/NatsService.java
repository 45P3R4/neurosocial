package com.aspera.neurosocial.common.nats_service;

import java.nio.charset.StandardCharsets;

import io.nats.client.Connection;
import io.nats.client.Dispatcher;
import io.nats.client.Subscription;

public class NatsService {

    private Connection connection;

    public NatsService(Connection connection) {
        this.connection = connection;
    }

    public void sendMessage(String subject, String message) {
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        connection.publish(subject, messageBytes);
    }

    public void setupSubscription() {
        String subject = "sending-message";

        Dispatcher dispatcher = connection.createDispatcher((msg) -> {});

        Subscription sub = dispatcher.subscribe(subject, (msg) -> {
            String response = new String(msg.getData(), StandardCharsets.UTF_8);
            System.err.println(response);
        });
        
        // sub.unsubscribe();
    }

}
