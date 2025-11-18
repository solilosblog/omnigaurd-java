package com.omnigaurd.solilos.controller;
import com.omnigaurd.solilos.model.ChatRequest;
import com.omnigaurd.solilos.model.ChatResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AiChat {

    private final ChatClient geminiChatClient;

    public AiChat(GoogleGenAiChatModel googleGenAiChatModel) {
        this.geminiChatClient = ChatClient.builder(googleGenAiChatModel).build();
    }

    @GetMapping("/health")
    public String health() {
        return "Working fine";
    }

    @PostMapping("/ai-chat-gemini")
    public ChatResponse chatWithGemini(@RequestBody ChatRequest request) {
        String systemPrompt = request.getSystemPrompt() != null ? request.getSystemPrompt() : "";
        String userMessage = request.getMessage();
        String fullPrompt = systemPrompt + "\n\n" + userMessage;

        if (request.getCodeContext() != null) {
            fullPrompt += "\n\nCode Context: " + request.getCodeContext().toString();
        }
        System.out.println("Fullprompt details===>"+fullPrompt);
        String content = geminiChatClient.prompt()
                .user(fullPrompt)
                .call()
                .content();

        int inputTokens = estimateTokens(fullPrompt);
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
