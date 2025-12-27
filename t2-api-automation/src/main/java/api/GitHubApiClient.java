package api;

import constants.ApiEndpoints;
import io.restassured.response.Response;
import models.Repository;
import utils.ConfigReader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class GitHubApiClient extends BaseApiClient {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    public List<Repository> getOrganizationRepositories(String organization) {
        String endpoint = ApiEndpoints.ORG_REPOS.replace("{org}", organization);
        Response response = get(endpoint);
        validateStatusCode(response, 200);
        
        try {
            return objectMapper.readValue(
                response.getBody().asString(),
                new TypeReference<List<Repository>>() {}
            );
        } catch (Exception e) {
            log.error("Failed to parse repositories", e);
            throw new RuntimeException("Failed to parse repositories", e);
        }
    }
    
    public List<Repository> getSeleniumHQRepositories() {
        String org = ConfigReader.getOrganization();
        log.info("Fetching repositories for organization: {}", org);
        return getOrganizationRepositories(org);
    }
    
    public Repository getRepository(String owner, String repoName) {
        String endpoint = String.format("/repos/%s/%s", owner, repoName);
        Response response = get(endpoint);
        validateStatusCode(response, 200);
        
        try {
            return objectMapper.readValue(
                response.getBody().asString(),
                Repository.class
            );
        } catch (Exception e) {
            log.error("Failed to parse repository", e);
            throw new RuntimeException("Failed to parse repository", e);
        }
    }
    
    public Repository getHighestRatedRepository(String organization) {
        List<Repository> repositories = getOrganizationRepositories(organization);
        
        if (repositories == null || repositories.isEmpty()) {
            throw new RuntimeException("No repositories found for organization: " + organization);
        }
        
        Repository highestRated = repositories.stream()
            .max((r1, r2) -> Integer.compare(r1.getStars(), r2.getStars()))
            .orElseThrow(() -> new RuntimeException("Failed to find highest rated repository"));
        
        log.info("Highest rated repository: {} with {} stars", 
            highestRated.getName(), highestRated.getStars());
        
        return highestRated;
    }
    
    public Repository getHighestRatedSeleniumHQRepository() {
        String org = ConfigReader.getOrganization();
        return getHighestRatedRepository(org);
    }
}

