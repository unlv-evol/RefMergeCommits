package edu.unlv.evol.refmergecommits.core;

public class Refactoring {
    private final static int MAX_DETAIL_LENGTH = 2000;
    private String type;
    private String detail;
    private RefactoringCommit refactoringCommit;
    private Project project;

    public Refactoring(String type, String detail, RefactoringCommit refactoringCommit, Project project) {
        this.type = type;
        this.detail = detail;
        this.refactoringCommit = refactoringCommit;
        this.project = project;
    }

    public static int getMaxDetailLength() {
        return MAX_DETAIL_LENGTH;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public RefactoringCommit getRefactoringCommit() {
        return refactoringCommit;
    }

    public void setRefactoringCommit(RefactoringCommit refactoringCommit) {
        this.refactoringCommit = refactoringCommit;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
