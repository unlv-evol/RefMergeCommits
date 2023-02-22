package edu.unlv.evol.refmergecommits.core;

public class Project {
    private String url;
    private String name;

    public Project() {
    }

    public Project(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
