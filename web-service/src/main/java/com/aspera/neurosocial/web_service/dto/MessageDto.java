package com.aspera.neurosocial.web_service.dto;

import java.time.LocalDateTime;


public class MessageDto {
        public long id;
        public long parent;
        public long author;
        public String text;
        public int rating;
        public LocalDateTime timestamp;
}