package edu.unlv.evol.refmergecommits.core;

public class ConflictingRegionHistory {
    private String commitHash;
    private int mergeParent;
    private int oldStartLine;
    private int oldLength;
    private String oldPath;
    private int  newStartLine;
    private int newLength;
    private String newPath;
    private ConflictingRegion conflictingRegion;
    private String authorName;
    private String authorEmail;
    private int timestamp;
//    private Project project;

    public ConflictingRegionHistory() {
    }

    public ConflictingRegionHistory(String commitHash, int mergeParent, int oldStartLine, int oldLength, String oldPath, int newStartLine, int newLength, String newPath, ConflictingRegion conflictingRegion, String authorName, String authorEmail, int timestamp) {
        this.commitHash = commitHash;
        this.mergeParent = mergeParent;
        this.oldStartLine = oldStartLine;
        this.oldLength = oldLength;
        this.oldPath = oldPath;
        this.newStartLine = newStartLine;
        this.newLength = newLength;
        this.newPath = newPath;
        this.conflictingRegion = conflictingRegion;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.timestamp = timestamp;
//        this.project = project;
    }

    public String getCommitHash() {
        return commitHash;
    }

    public void setCommitHash(String commitHash) {
        this.commitHash = commitHash;
    }

    public int getMergeParent() {
        return mergeParent;
    }

    public void setMergeParent(int mergeParent) {
        this.mergeParent = mergeParent;
    }

    public int getOldStartLine() {
        return oldStartLine;
    }

    public void setOldStartLine(int oldStartLine) {
        this.oldStartLine = oldStartLine;
    }

    public int getOldLength() {
        return oldLength;
    }

    public void setOldLength(int oldLength) {
        this.oldLength = oldLength;
    }

    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public int getNewStartLine() {
        return newStartLine;
    }

    public void setNewStartLine(int newStartLine) {
        this.newStartLine = newStartLine;
    }

    public int getNewLength() {
        return newLength;
    }

    public void setNewLength(int newLength) {
        this.newLength = newLength;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public ConflictingRegion getConflictingRegion() {
        return conflictingRegion;
    }

    public void setConflictingRegion(ConflictingRegion conflictingRegion) {
        this.conflictingRegion = conflictingRegion;
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

//    public Project getProject() {
//        return project;
//    }
//
//    public void setProject(Project project) {
//        this.project = project;
//    }
}
