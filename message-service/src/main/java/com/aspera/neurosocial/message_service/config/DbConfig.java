package com.aspera.neurosocial.message_service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class DbConfig {

    @Value("${neurosocial.db.address}")
    String dbAddress;

    @Value("${neurosocial.db.user}")
    String dbUser;

    @Value("${neurosocial.db.password}")
    String dbPassword;
}
