package com.rekam.medis.component;

import java.io.IOException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Reads the java property file config.properties <br>
 * and retrieves its property values
 *
 */
public final class ConfigurationHolder {

    private static final Properties properties;
    
    static {

        properties = new Properties();
        
        Resource resource = new ClassPathResource("config.properties");
        
        try {
            
            properties.load(resource.getInputStream());
            
        } catch (IOException ex) {
            
        	System.out.println("error load configuration properties.message : " + ex.getMessage());
        }
    }
    
    public static String get(String key) {
        
        return properties.getProperty(key);
    }
    
}
