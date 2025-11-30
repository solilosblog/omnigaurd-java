package com.omnigaurd.solilos.controller;

import com.omnigaurd.solilos.model.ChatRequest;
import com.omnigaurd.solilos.model.ChatResponse;
import com.omnigaurd.solilos.service.ContextEnricher;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AiChat {

    private final ChatClient geminiChatClient;
    private final ContextEnricher contextEnricher;

    public AiChat(GoogleGenAiChatModel googleGenAiChatModel, ContextEnricher contextEnricher) {
        this.geminiChatClient = ChatClient.builder(googleGenAiChatModel).build();
        this.contextEnricher = contextEnricher;
    }

    @GetMapping("/health")
    public String health() {
        return "Working fine";
    }

    @PostMapping("/ai-chat-gemini")
    public ChatResponse chatWithGemini(@RequestBody ChatRequest request) {
        String enrichedPrompt = contextEnricher.buildEnrichedPrompt(
                request.getMessage(),
                request.getSystemPrompt(),
                request.getCodebaseContext()
        );

        System.out.println("Enriched Prompt Length: " + enrichedPrompt.length());

        String content = geminiChatClient.prompt()
                .user(enrichedPrompt)
                .call()
                .content();

        int inputTokens = estimateTokens(enrichedPrompt);
        int outputTokens = estimateTokens(content);

        return new ChatResponse(
                content,
                new ChatResponse.TokenUsage(inputTokens, outputTokens)
        );
    }

    private int estimateTokens(String text) {
        return text.split("\\s+").length;
    }
}