package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository {
    
    private String name;
    
    @JsonProperty("stargazers_count")
    private int stars;
    
    @JsonProperty("open_issues_count")
    private int openIssues;
    
    @JsonProperty("html_url")
    private String url;
    
    private String language;
    
    // Getters and Setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getStars() {
        return stars;
    }
    
    public void setStars(int stars) {
        this.stars = stars;
    }
    
    public int getOpenIssues() {
        return openIssues;
    }
    
    public void setOpenIssues(int openIssues) {
        this.openIssues = openIssues;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    @Override
    public String toString() {
        return String.format("%s (⭐%d, Issues: %d)", name, stars, openIssues);
    }
}