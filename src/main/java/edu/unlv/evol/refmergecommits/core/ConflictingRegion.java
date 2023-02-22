package edu.unlv.evol.refmergecommits.core;

public class ConflictingRegion {
    private int startLineParent1;
    private int lengthParent1;
    private String pathParent1;
    private int startLineParent2;
    private int lengthParent2;
    private String pathParent2;
    private ConflictingJavaFile conflictingJavaFile;
//    private Project project;

    public ConflictingRegion() {
    }

    public ConflictingRegion(int startLineParent1, int lengthParent1, String pathParent1, int startLineParent2, int lengthParent2, String pathParent2, ConflictingJavaFile conflictingJavaFile) {
        this.startLineParent1 = startLineParent1;
        this.lengthParent1 = lengthParent1;
        this.pathParent1 = pathParent1;
        this.startLineParent2 = startLineParent2;
        this.lengthParent2 = lengthParent2;
        this.pathParent2 = pathParent2;
        this.conflictingJavaFile = conflictingJavaFile;
//        this.project = project;
    }

    public int getStartLineParent1() {
        return startLineParent1;
    }

    public void setStartLineParent1(int startLineParent1) {
        this.startLineParent1 = startLineParent1;
    }

    public int getLengthParent1() {
        return lengthParent1;
    }

    public void setLengthParent1(int lengthParent1) {
        this.lengthParent1 = lengthParent1;
    }

    public String getPathParent1() {
        return pathParent1;
    }

    public void setPathParent1(String pathParent1) {
        this.pathParent1 = pathParent1;
    }

    public int getStartLineParent2() {
        return startLineParent2;
    }

    public void setStartLineParent2(int startLineParent2) {
        this.startLineParent2 = startLineParent2;
    }

    public int getLengthParent2() {
        return lengthParent2;
    }

    public void setLengthParent2(int lengthParent2) {
        this.lengthParent2 = lengthParent2;
    }

    public String getPathParent2() {
        return pathParent2;
    }

    public void setPathParent2(String pathParent2) {
        this.pathParent2 = pathParent2;
    }

    public ConflictingJavaFile getConflictingJavaFile() {
        return conflictingJavaFile;
    }

    public void setConflictingJavaFile(ConflictingJavaFile conflictingJavaFile) {
        this.conflictingJavaFile = conflictingJavaFile;
    }
//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }
}
