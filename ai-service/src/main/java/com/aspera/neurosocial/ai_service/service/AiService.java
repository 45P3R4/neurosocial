package com.aspera.neurosocial.ai_service.service;

import org.springframework.web.client.RestTemplate;

import com.aspera.neurosocial.dto.AiRequestDto;
import com.aspera.neurosocial.dto.AiResponseDto;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AiService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:11434/api/v1/chat";

    public AiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AiResponseDto generateMessage(String model_name, String system_prompt, String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        AiRequestDto aiRequest = new AiRequestDto(model_name, system_prompt, prompt);

        HttpEntity<AiRequestDto> entity = new HttpEntity<>(aiRequest, headers);
        ResponseEntity<AiResponseDto> response = restTemplate.postForEntity(BASE_URL, entity, AiResponseDto.class);

        System.err.println("\n" + response.getBody());
        return response.getBody();
    }
}
