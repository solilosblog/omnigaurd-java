package com.omnigaurd.solilos.model;

import java.util.List;
import java.util.Map;

public class CodebaseContext {
    private String mode;
    private Integer batchNumber;
    private Integer totalBatches;
    private GraphData graph;
    private List<FileData> files;
    private ConversationContext conversationContext;

    public String getMode() { return mode; }
    public void setMode(String mode) { this.mode = mode; }
    public Integer getBatchNumber() { return batchNumber; }
    public void setBatchNumber(Integer batchNumber) { this.batchNumber = batchNumber; }
    public Integer getTotalBatches() { return totalBatches; }
    public void setTotalBatches(Integer totalBatches) { this.totalBatches = totalBatches; }
    public GraphData getGraph() { return graph; }
    public void setGraph(GraphData graph) { this.graph = graph; }
    public List<FileData> getFiles() { return files; }
    public void setFiles(List<FileData> files) { this.files = files; }
    public ConversationContext getConversationContext() { return conversationContext; }
    public void setConversationContext(ConversationContext conversationContext) { this.conversationContext = conversationContext; }

    public static class GraphData {
        private String mermaid;
        private String tree;
        private Map<String, Object> metadata;

        public String getMermaid() { return mermaid; }
        public void setMermaid(String mermaid) { this.mermaid = mermaid; }
        public String getTree() { return tree; }
        public void setTree(String tree) { this.tree = tree; }
        public Map<String, Object> getMetadata() { return metadata; }
        public void setMetadata(Map<String, Object> metadata) { this.metadata = metadata; }
    }

    public static class FileData {
        private String path;
        private String language;
        private String role;
        private Map<String, Object> summary;
        private String code;
        private Dependencies dependencies;

        public String getPath() { return path; }
        public void setPath(String path) { this.path = path; }
        public String getLanguage() { return language; }
        public void setLanguage(String language) { this.language = language; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
        public Map<String, Object> getSummary() { return summary; }
        public void setSummary(Map<String, Object> summary) { this.summary = summary; }
        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public Dependencies getDependencies() { return dependencies; }
        public void setDependencies(Dependencies dependencies) { this.dependencies = dependencies; }
    }

    public static class Dependencies {
        private List<String> imports;
        private List<String> importedBy;
        private List<String> transitive;

        public List<String> getImports() { return imports; }
        public void setImports(List<String> imports) { this.imports = imports; }
        public List<String> getImportedBy() { return importedBy; }
        public void setImportedBy(List<String> importedBy) { this.importedBy = importedBy; }
        public List<String> getTransitive() { return transitive; }
        public void setTransitive(List<String> transitive) { this.transitive = transitive; }
    }

    public static class ConversationContext {
        private List<Integer> previousBatches;
        private List<String> keyTakeaways;
        private String userGoal;

        public List<Integer> getPreviousBatches() { return previousBatches; }
        public void setPreviousBatches(List<Integer> previousBatches) { this.previousBatches = previousBatches; }
        public List<String> getKeyTakeaways() { return keyTakeaways; }
        public void setKeyTakeaways(List<String> keyTakeaways) { this.keyTakeaways = keyTakeaways; }
        public String getUserGoal() { return userGoal; }
        public void setUserGoal(String userGoal) { this.userGoal = userGoal; }
    }
}