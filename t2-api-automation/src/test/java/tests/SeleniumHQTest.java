package tests;

import api.GitHubApiClient;
import models.Repository;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SeleniumHQTest {
    
    private GitHubApiClient gitHubApiClient;
    
    @BeforeClass
    public void setUp() {
        gitHubApiClient = new GitHubApiClient();
    }
    
    @Test
    public void testGetSeleniumHQRepositories() {
        List<Repository> repositories = gitHubApiClient.getSeleniumHQRepositories();
        
        Assert.assertNotNull(repositories, "Repositories list should not be null");
        Assert.assertFalse(repositories.isEmpty(), "Repositories list should not be empty");
        
        System.out.println("Found " + repositories.size() + " repositories:");
        repositories.forEach(repo -> System.out.println("  - " + repo));
    }
    
    @Test
    public void testGetSeleniumRepository() {
        Repository seleniumRepo = gitHubApiClient.getRepository("seleniumhq", "selenium");
        
        Assert.assertNotNull(seleniumRepo, "Repository should not be null");
        Assert.assertEquals(seleniumRepo.getName(), "selenium", "Repository name should be 'selenium'");
        Assert.assertNotNull(seleniumRepo.getUrl(), "Repository URL should not be null");
        Assert.assertTrue(seleniumRepo.getStars() > 0, "Repository should have stars");
        
        System.out.println("Repository details: " + seleniumRepo);
    }
    
    @Test
    public void testRepositoryHasValidData() {
        List<Repository> repositories = gitHubApiClient.getSeleniumHQRepositories();
        
        for (Repository repo : repositories) {
            Assert.assertNotNull(repo.getName(), "Repository name should not be null");
            Assert.assertNotNull(repo.getUrl(), "Repository URL should not be null");
            Assert.assertTrue(repo.getStars() >= 0, "Stars count should be non-negative");
            Assert.assertTrue(repo.getOpenIssues() >= 0, "Open issues count should be non-negative");
        }
    }
    
    @Test
    public void testGetHighestRatedRepository() {
        Repository highestRated = gitHubApiClient.getHighestRatedSeleniumHQRepository();
        
        Assert.assertNotNull(highestRated, "Highest rated repository should not be null");
        Assert.assertNotNull(highestRated.getName(), "Repository name should not be null");
        Assert.assertTrue(highestRated.getStars() > 0, "Highest rated repository should have stars > 0");
        
        // Verify it's actually the highest by comparing with all repositories
        List<Repository> allRepos = gitHubApiClient.getSeleniumHQRepositories();
        int maxStars = allRepos.stream()
            .mapToInt(Repository::getStars)
            .max()
            .orElse(0);
        
        Assert.assertEquals(highestRated.getStars(), maxStars, 
            "Highest rated repository should have the maximum stars");
        
        System.out.println("\n=== Highest Rated Repository ===");
        System.out.println("Name: " + highestRated.getName());
        System.out.println("Stars: " + highestRated.getStars());
        System.out.println("URL: " + highestRated.getUrl());
        System.out.println("Language: " + highestRated.getLanguage());
        System.out.println("Open Issues: " + highestRated.getOpenIssues());
    }
}
