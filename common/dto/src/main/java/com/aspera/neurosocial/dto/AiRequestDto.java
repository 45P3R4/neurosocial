package com.aspera.neurosocial.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AiRequestDto {

    @JsonProperty("model")
    private String model;

    @JsonProperty("system_prompt")
    private String system_prompt;

    @JsonProperty("input")
    private String input;
}
