package com.aspera.neurosocial.web_service.dto;

import java.time.LocalDateTime;

public record MessageDto(
        long id,
        long parent,
        long author,
        String text,
        int rating,
        LocalDateTime timestamp) {
}
