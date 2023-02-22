package edu.unlv.evol.refmergecommits.core;

public class RefactoringCommit {
    private String commitHash;
    private Project project;
    private boolean timedOut = false;

    public RefactoringCommit() {
    }

    public RefactoringCommit(String commitHash, Project project) {
        this.commitHash = commitHash;
        this.project = project;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public boolean getTimedOut() {
        return timedOut;
    }

    public void setTimedOut(boolean timedOut) {
        this.timedOut = timedOut;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
