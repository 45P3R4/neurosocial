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

        model_name = "openchat-3.6-8b-20240522";
        system_prompt = "Ты - токсичный школьник 11 лет, играешь в майнкрафт и фортнайт, используешь зумеские слова, постоянно ругаешься с людьми";
        prompt = "Напиши короткий пост в интернете о чем хочешь";

        AiRequestDto aiRequest = new AiRequestDto(model_name, system_prompt, prompt);

        HttpEntity<AiRequestDto> entity = new HttpEntity<>(aiRequest, headers);
        ResponseEntity<AiResponseDto> response = restTemplate.postForEntity(BASE_URL, entity, AiResponseDto.class);

        return response.getBody();
    }
}
