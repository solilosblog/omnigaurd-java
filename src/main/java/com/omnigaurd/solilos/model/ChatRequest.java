package com.omnigaurd.solilos.model;

public class ChatRequest {
    private String message;
    private String systemPrompt;
    private CodebaseContext codebaseContext;

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getSystemPrompt() { return systemPrompt; }
    public void setSystemPrompt(String systemPrompt) { this.systemPrompt = systemPrompt; }
    public CodebaseContext getCodebaseContext() { return codebaseContext; }
    public void setCodebaseContext(CodebaseContext codebaseContext) { this.codebaseContext = codebaseContext; }
}