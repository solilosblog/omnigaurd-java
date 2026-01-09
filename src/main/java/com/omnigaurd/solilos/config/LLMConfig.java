package com.omnigaurd.solilos.config;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Configuration for LLM providers
 * Supports multiple providers: Groq, OpenRouter, Mistral, Together AI, etc.
 */
@Configuration
public class LLMConfig {
    
    @Value("${spring.ai.openai.api-key}")
    private String apiKey;
    
    @Value("${spring.ai.openai.base-url}")
    private String baseUrl;
    
    @Value("${spring.ai.openai.chat.options.model}")
    private String model;
    
    @Value("${spring.ai.openai.chat.options.temperature:0.7}")
    private Double temperature;
    
    @Value("${spring.ai.openai.chat.options.max-tokens:8000}")
    private Integer maxTokens;
    
    @Bean
    @Primary
    public ChatModel chatModel() {
        System.out.println("🔧 Initializing LLM Configuration");
        System.out.println("📡 Base URL: " + baseUrl);
        System.out.println("🤖 Model: " + model);
        System.out.println("🌡️ Temperature: " + temperature);
        System.out.println("📊 Max Tokens: " + maxTokens);
        
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .withModel(model)
                .withTemperature(temperature)
                .withMaxTokens(maxTokens)
                .build();
        
        OpenAiApi openAiApi = new OpenAiApi(baseUrl, apiKey);
        
        return new OpenAiChatModel(openAiApi, options);
    }
}