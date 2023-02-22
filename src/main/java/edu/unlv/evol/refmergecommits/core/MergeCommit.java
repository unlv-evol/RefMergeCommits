package edu.unlv.evol.refmergecommits.core;

public class MergeCommit {
    private String commitHash;
    private boolean isConflicting;
    private String parent1;
    private String parent2;
    private Project project;
    private String authorName;
    private String authorEmail;
    private int timestamp;

    public MergeCommit() {
    }

    public MergeCommit(String commitHash, boolean isConflicting, String parent1, String parent2, Project project, String authorName, String authorEmail, int timestamp) {
        this.commitHash = commitHash;
        this.isConflicting = isConflicting;
        this.parent1 = parent1;
        this.parent2 = parent2;
        this.project = project;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.timestamp = timestamp;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public boolean isConflicting() {
        return isConflicting;
    }

    public void setConflicting(boolean conflicting) {
        isConflicting = conflicting;
    }

    public String getParent1() {
        return parent1;
    }

    public void setParent1(String parent1) {
        this.parent1 = parent1;
    }

    public String getParent2() {
        return parent2;
    }

    public void setParent2(String parent2) {
        this.parent2 = parent2;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }
}
