package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ConfigReader;

public abstract class BaseApiClient {
    
    protected static final Logger log = LoggerFactory.getLogger(BaseApiClient.class);
    
    static {
        RestAssured.baseURI = ConfigReader.getBaseUri();
    }
    
    protected RequestSpecification getBaseRequest() {
        return RestAssured.given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .header("User-Agent", "RestAssured-Test");
    }
    
    protected Response get(String endpoint, Object... params) {
        log.info("GET: {}", endpoint);
        Response response = getBaseRequest().get(endpoint, params);
        log.info("Status: {} | Time: {}ms", response.getStatusCode(), response.getTime());
        return response;
    }
    
    protected void validateStatusCode(Response response, int expectedStatus) {
        if (response.getStatusCode() != expectedStatus) {
            log.error("Expected: {}, Got: {}", expectedStatus, response.getStatusCode());
        }
        response.then().statusCode(expectedStatus);
    }
}