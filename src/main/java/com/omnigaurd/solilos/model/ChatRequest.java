package com.omnigaurd.solilos.model;

public class ChatRequest {
    private String message;
    private String systemPrompt;
    private Object codeContext;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getSystemPrompt() { return systemPrompt; }
    public void setSystemPrompt(String systemPrompt) { this.systemPrompt = systemPrompt; }
    public Object getCodeContext() { return codeContext; }
    public void setCodeContext(Object codeContext) { this.codeContext = codeContext; }
}