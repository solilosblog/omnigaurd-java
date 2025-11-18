package com.omnigaurd.solilos.model;

public class ChatResponse {
    private String content;
    private TokenUsage tokens;

    public ChatResponse(String content, TokenUsage tokens) {
        this.content = content;
        this.tokens = tokens;
    }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public TokenUsage getTokens() { return tokens; }
    public void setTokens(TokenUsage tokens) { this.tokens = tokens; }

    public static class TokenUsage {
        private int input;
        private int output;
        private int total;

        public TokenUsage(int input, int output) {
            this.input = input;
            this.output = output;
            this.total = input + output;
        }

        public int getInput() { return input; }
        public int getOutput() { return output; }
        public int getTotal() { return total; }
    }
}