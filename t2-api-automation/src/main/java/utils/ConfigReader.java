package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    
    private static Properties properties;
    
    static {
        loadProperties();
    }
    
    private static void loadProperties() {
        properties = new Properties();
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            
            if (input == null) {
                throw new RuntimeException("config.properties not found");
            }
            
            properties.load(input);
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }
    
    public static String getBaseUri() {
        return properties.getProperty("api.base.uri");
    }
    
    public static String getOrganization() {
        return properties.getProperty("github.organization");
    }
}