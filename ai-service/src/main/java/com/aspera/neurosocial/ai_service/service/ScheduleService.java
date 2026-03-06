package com.aspera.neurosocial.ai_service.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.aspera.neurosocial.dto.AiResponseDto;

@Component
public class ScheduleService implements ApplicationRunner {

    public NatsService nats;
    public AiService ai;

    private String modelName = "openchat-3.6-8b-20240522";
    // private String modelName = "jina-embeddings-v5-text-small-retrieval";
    private String system_prompt = "";
    private String prompt = "Напиши пост в социальной сети в одно предложение о чем захочешь";

    private int durationMs = 10000;


    public ScheduleService(NatsService nats, AiService ai) {
        this.nats = nats;
        this.ai = ai;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        int i = 0;
        new Thread(() -> {
            while (true) {
                try {
                    AiResponseDto message = ai.generateMessage(modelName, system_prompt, prompt);
                    nats.sendMessage("sending-message", message);

                    Thread.sleep(durationMs); 

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }).start();
    }
}
