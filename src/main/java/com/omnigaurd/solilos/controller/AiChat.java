package com.omnigaurd.solilos.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.omnigaurd.solilos.model.ChatRequest;
import com.omnigaurd.solilos.model.ChatResponse;
import com.omnigaurd.solilos.service.ContextEnricher;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AiChat {

    private final ChatClient chatClient;
    private final ContextEnricher contextEnricher;
    private final OpenAiChatModel chatModel;

    public AiChat(OpenAiChatModel openAiChatModel, ContextEnricher contextEnricher) {
        this.chatModel = openAiChatModel;
        this.chatClient = ChatClient.builder(openAiChatModel).build();
        this.contextEnricher = contextEnricher;
    }

    @GetMapping("/health")
    public String health() {
        return "✅ Solilos API is working fine! Using model: " + 
               chatModel.getDefaultOptions().getModel();
    }

    @PostMapping("/ai-chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {
        try {
            String enrichedPrompt = contextEnricher.buildEnrichedPrompt(
                    request.getMessage(),
                    request.getSystemPrompt(),
                    request.getCodebaseContext()
            );

            System.out.println("📝 Enriched Prompt Length: " + enrichedPrompt.length());
            System.out.println("🤖 Using Model: " + chatModel.getDefaultOptions().getModel());

            String content = chatClient.prompt()
                    .user(enrichedPrompt)
                    .call()
                    .content();

            int inputTokens = estimateTokens(enrichedPrompt);
            int outputTokens = estimateTokens(content);

            System.out.println("✅ Response generated successfully!");
            System.out.println("📊 Input tokens: " + inputTokens + ", Output tokens: " + outputTokens);

            return new ChatResponse(
                    content,
                    new ChatResponse.TokenUsage(inputTokens, outputTokens)
            );
        } catch (Exception e) {
            System.err.println("❌ Error generating response: " + e.getMessage());
            e.printStackTrace();
            
            return new ChatResponse(
                    "Error: " + e.getMessage(),
                    new ChatResponse.TokenUsage(0, 0)
            );
        }
    }

    /**
     * Estimates token count by counting words
     * Note: This is a rough approximation. Real tokens may vary by ~25%
     */
    private int estimateTokens(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        // Rough approximation: 1 token ≈ 0.75 words
        int wordCount = text.split("\\s+").length;
        return (int) Math.ceil(wordCount * 1.33);
    }
}