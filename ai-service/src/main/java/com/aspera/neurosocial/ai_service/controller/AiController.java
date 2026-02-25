package com.aspera.neurosocial.ai_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aspera.neurosocial.ai_service.service.AiService;
import com.aspera.neurosocial.common.nats_service.NatsService;
import com.aspera.neurosocial.dto.AiResponseDto;

@RestController
public class AiController {

    private final AiService aiService;
    private final NatsService nats;

    public AiController(AiService aiService, NatsService nats) {
        this.aiService = aiService;
        this.nats = nats;
    }

    @GetMapping("/")
    private String writeText() {
        AiResponseDto response = aiService.generateMessage("", "", "");
        nats.sendMessage("sending-message", response.getOutput().toString());
        return response.getOutput().toString();
    }
}
