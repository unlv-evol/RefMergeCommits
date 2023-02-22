package edu.unlv.evol.refmergecommits;

import edu.unlv.evol.refmergecommits.core.*;
import edu.unlv.evol.refmergecommits.utils.GitUtils;
import edu.unlv.evol.refmergecommits.utils.Utils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.JGitInternalException;
import org.eclipse.jgit.revwalk.RevCommit;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class AnalyzeMergeCommits {
    private String repoUrl; //---https://github.com/objectify/objectify---
    private String clonePath;
    private List<String> listRepoUrls;

    public AnalyzeMergeCommits(String repoUrl, String clonePath) {
        this.repoUrl = repoUrl;
        this.clonePath = clonePath;
    }

    public AnalyzeMergeCommits(String clonePath, List<String> listRepoUrls) {
        this.clonePath = clonePath;
        this.listRepoUrls = listRepoUrls;
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl;
    }

    public String getClonePath() {
        return clonePath;
    }

    public void setClonePath(String clonePath) {
        this.clonePath = clonePath;
    }

    public List<String> getListRepoUrls() {
        return listRepoUrls;
    }

    public void setListRepoUrls(List<String> listRepoUrls) {
        this.listRepoUrls = listRepoUrls;
    }

    public void start() {
//        try {
//            DatabaseUtils.createDatabase();
//            runParallel(parallelism);
//        } catch (Throwable e) {
//            Utils.log(null, e);
//            e.printStackTrace();
//        }
        cloneAndAnalyzeProject(getRepoUrl());
    }

    private void cloneAndAnalyzeProject(String projectURL) {
        String projectName = projectURL.substring(projectURL.lastIndexOf('/') + 1);
        Project project = new Project(projectURL, projectName);

        try {
            removeProject(projectName);
            cloneProject(projectURL);
            analyzeProject(project);
            Utils.log(projectName, "Finished the analysis, removing the repository...");
            removeProject(projectName);
            Utils.log(projectName, "Done with " + projectName);
        } catch (JGitInternalException | GitAPIException | IOException e) {
            Utils.log(projectName, e);
            e.printStackTrace();
        }
    }

    private void cloneProject(String url) throws GitAPIException {
        String projectName = url.substring(url.lastIndexOf('/') + 1);
        Utils.log(projectName, String.format("Cloning %s...", projectName));
        Git.cloneRepository()
                .setURI(url)
                .setDirectory(new File(clonePath, projectName))
                .call();
    }

    private void removeProject(String projectName) {
        try {
            Files.walk(Paths.get(new File(clonePath, projectName).getAbsolutePath()))
                    .sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void analyzeProject(Project project) throws GitAPIException, IOException {
        Utils.log(project.getName(), String.format("Analyzing %s's commits...", project.getName()));
        analyzeProjectCommits(project);
        Utils.log(project.getName(), String.format("Analyzing %s with RefMiner...", project.getName()));
//        analyzeProjectWithRefMiner(project);
    }

    private void analyzeProjectCommits(Project project) throws GitAPIException, IOException {
        GitUtils gitUtils = new GitUtils(new File(clonePath, project.getName()));
        Iterable<RevCommit> mergeCommits = gitUtils.getMergeCommits();
        int mergeCommitIndex = 0;
        Map<String, String> conflictingJavaFiles = new HashMap<>();
//        List<Object> mergeCommitsList = new ArrayList<>();
        for (RevCommit mergeCommit : mergeCommits) {
            mergeCommitIndex++;
            Utils.log(project.getName(), String.format("Analyzing commit %.7s... (%d/?)", mergeCommit.getName(),
                    mergeCommitIndex));

            try {
                conflictingJavaFiles.clear();
                boolean isConflicting = gitUtils.isConflicting(mergeCommit, conflictingJavaFiles);

                MergeCommit mergeCommitModel = new MergeCommit(mergeCommit.getName(), isConflicting,
                        mergeCommit.getParent(0).getName(), mergeCommit.getParent(1).getName(), project,
                        mergeCommit.getAuthorIdent().getName(), mergeCommit.getAuthorIdent().getEmailAddress(),
                        mergeCommit.getCommitTime());

//                mergeCommitsList.add(mergeCommitModel);
//            extractConflictingRegions(gitUtils, mergeCommitModel, conflictingJavaFiles);
                System.out.println(mergeCommitModel.getCommitHash() + "," + mergeCommitModel.isConflicting() + "," + mergeCommitModel.getParent1() + "," + mergeCommitModel.getParent2() + "," + mergeCommitModel.getProject().getName());
            } catch (GitAPIException e) {
                Utils.log(project.getName(), e);
                e.printStackTrace();
            }
            conflictingJavaFiles.clear();
        }
    }

    private void extractConflictingRegions(GitUtils gitUtils, MergeCommit mergeCommit,
                                           Map<String, String> conflictingJavaFiles) {
        List<int[][]> conflictingRegions = new ArrayList<>();
        List<GitUtils.CodeRegionChange> leftConflictingRegionHistory = new ArrayList<>();
        List<GitUtils.CodeRegionChange> rightConflictingRegionHistory = new ArrayList<>();

        for (String path : conflictingJavaFiles.keySet()) {
            String conflictType = conflictingJavaFiles.get(path);
            ConflictingJavaFile conflictingJavaFile = new ConflictingJavaFile(path, conflictType, mergeCommit);
//        conflictingJavaFile.saveIt();

            if (conflictType.equalsIgnoreCase("content") ||
                    conflictType.equalsIgnoreCase("add/add")) {
                String[] conflictingRegionPaths = new String[2];
                conflictingRegions.clear();
                gitUtils.getConflictingRegions(path, conflictingRegionPaths, conflictingRegions);

                for (int[][] conflictingLines : conflictingRegions) {
                    ConflictingRegion conflictingRegion = new ConflictingRegion(
                            conflictingLines[0][0], conflictingLines[0][1], conflictingRegionPaths[0],
                            conflictingLines[1][0], conflictingLines[1][1], conflictingRegionPaths[1],
                            conflictingJavaFile);
//                conflictingRegion.saveIt();

                    leftConflictingRegionHistory.clear();
                    rightConflictingRegionHistory.clear();
                    gitUtils.getConflictingRegionHistory(mergeCommit.getParent1(), mergeCommit.getParent2(),
                            path, conflictingLines[0], leftConflictingRegionHistory);
                    gitUtils.getConflictingRegionHistory(mergeCommit.getParent2(), mergeCommit.getParent1(),
                            path, conflictingLines[1], rightConflictingRegionHistory);

                    leftConflictingRegionHistory.forEach(codeRegionChange -> {
                        RevCommit commit = gitUtils.populateCommit(codeRegionChange.commitHash);
                        String authorName = commit == null ? null : commit.getAuthorIdent().getName();
                        String authorEmail = commit == null ? null : commit.getAuthorIdent().getEmailAddress();
                        int timestamp = commit == null ? 0 : commit.getCommitTime();
                        new ConflictingRegionHistory(
                                codeRegionChange.commitHash, 1,
                                codeRegionChange.oldStartLine, codeRegionChange.oldLength, codeRegionChange.oldPath,
                                codeRegionChange.newStartLine, codeRegionChange.newLength, codeRegionChange.newPath,
                                conflictingRegion, authorName, authorEmail, timestamp);//.saveIt();
                    });
                    rightConflictingRegionHistory.forEach(codeRegionChange -> {
                        RevCommit commit = gitUtils.populateCommit(codeRegionChange.commitHash);
                        String authorName = commit == null ? null : commit.getAuthorIdent().getName();
                        String authorEmail = commit == null ? null : commit.getAuthorIdent().getEmailAddress();
                        int timestamp = commit == null ? 0 : commit.getCommitTime();
                        new ConflictingRegionHistory(
                                codeRegionChange.commitHash, 2,
                                codeRegionChange.oldStartLine, codeRegionChange.oldLength, codeRegionChange.oldPath,
                                codeRegionChange.newStartLine, codeRegionChange.newLength, codeRegionChange.newPath,
                                conflictingRegion, authorName, authorEmail, timestamp);//.saveIt();
                    });
                }
            }
        }
        conflictingRegions.clear();
        leftConflictingRegionHistory.clear();
        rightConflictingRegionHistory.clear();
    }

}
