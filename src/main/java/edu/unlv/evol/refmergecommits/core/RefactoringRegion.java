package edu.unlv.evol.refmergecommits.core;

public class RefactoringRegion {
    private char type;
    private String path;
    private int startLine;
    private int length;
    private Refactoring refactoring;

    public RefactoringRegion() {
    }

    public RefactoringRegion(char type, String path, int startLine, int length, Refactoring refactoring) {
        this.type = type;
        this.path = path;
        this.startLine = startLine;
        this.length = length;
        this.refactoring = refactoring;
    }

    public String getType() {
        return String.valueOf(type).toLowerCase();
    }

    public void setType(char type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStartLine() {
        return startLine;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Refactoring getRefactoring() {
        return refactoring;
    }

    public void setRefactoring(Refactoring refactoring) {
        this.refactoring = refactoring;
    }
}
