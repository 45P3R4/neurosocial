package com.aspera.neurosocial.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    @JsonProperty("id")
    long id;

    @JsonProperty("parent_id")
    long parent_id;

    @JsonProperty("author")
    String author;

    @JsonProperty("text")
    String text;

    @JsonProperty("rating")
    int rating;

    @JsonProperty("timestamp")
    LocalDateTime timestamp;

    @JsonProperty("aiRequest")
    AiRequestDto aiRequest;

    @JsonProperty("aiResponse")
    AiResponseDto aiResponse;
}
