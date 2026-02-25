package com.aspera.neurosocial.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiResponseDto {
    
    @JsonProperty("model_instance_id")
    private String modelInstanceId;
    
    private List<Output> output;
    private Stats stats;
    
    @JsonProperty("response_id")
    private String responseId;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Output {
    @JsonProperty("type")
    private String type;

    @JsonProperty("content")
    private String content;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Stats {
    
    @JsonProperty("input_tokens")
    private int inputTokens;
    
    @JsonProperty("total_output_tokens")
    private int totalOutputTokens;
    
    @JsonProperty("reasoning_output_tokens")
    private int reasoningOutputTokens;
    
    @JsonProperty("tokens_per_second")
    private double tokensPerSecond;
    
    @JsonProperty("time_to_first_token_seconds")
    private double timeToFirstTokenSeconds;
}
