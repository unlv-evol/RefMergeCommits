package edu.unlv.evol.refmergecommits.core;

public class ConflictingJavaFile {
    private String path;
    private String type;
    private MergeCommit mergeCommit;
    private Project project;

    public ConflictingJavaFile() {
    }

    public ConflictingJavaFile(String path, String type, MergeCommit mergeCommit) {
        this.path = path;
        this.type = type;
        this.mergeCommit = mergeCommit;
//        this.project = project;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MergeCommit getMergeCommit() {
        return mergeCommit;
    }

    public void setMergeCommit(MergeCommit mergeCommit) {
        this.mergeCommit = mergeCommit;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}

