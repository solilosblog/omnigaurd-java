package com.omnigaurd.solilos.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.google.genai.GoogleGenAiChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AiChat {

    private final ChatClient openAiChatClient;
    private final ChatClient geminiChatClient;

    public AiChat(OpenAiChatModel openAiChatModel,
                  GoogleGenAiChatModel googleGenAiChatModel) {
        this.openAiChatClient = ChatClient.builder(openAiChatModel).build();
        this.geminiChatClient = ChatClient.builder(googleGenAiChatModel).build();
    }

    @GetMapping("/health")
    public String health(){
        return "Working fine";
    }

    @GetMapping("/ai-chat-openai")
    public String chatWithOpenAi(){
        return openAiChatClient.prompt()
                .user("Say this is a test")
                .call()
                .content();
    }

    @GetMapping("/ai-chat-gemini")
    public String chatWithGemini(){
        return geminiChatClient.prompt()
                .user("@GetMapping(\"/ai-chat-openai\")\n" +
                        "    public String chatWithOpenAi(){\n" +
                        "        return openAiChatClient.prompt()\n" +
                        "                .user(\"Say this is a test\")\n" +
                        "                .call()\n" +
                        "                .content();\n" +
                        "    } " +
                        "can you write the unit test for me for this controller? i am using java + spring boot + mavent + use junit + mockit for writing test")
                .call()
                .content();
    }
}