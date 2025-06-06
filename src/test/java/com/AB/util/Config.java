package com.AB.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log = LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES = "config/default.properties";
    private static Properties properties;

    public static void initialize() throws IOException {

        // Load default properties
        properties = loadDefaultProperties();

        //check for any override

        for (String key : properties.stringPropertyNames())
        {
            if (System.getProperties().containsKey(key))    //checking if any values coming from command line
            {
                properties.setProperty(key, System.getProperty(key)); //overriding the default value
            }
        }

        //printing the final set of properties

        log.info("Test properties:");
        log.info("-----------------------------------------");
        for (String key : properties.stringPropertyNames())
        {
            log.info(key + " = " + properties.getProperty(key));
        }
        log.info("-----------------------------------------");

    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static Properties loadDefaultProperties() throws IOException {

        Properties properties = new Properties();
        try(InputStream stream = ResourceLoader.getResource(DEFAULT_PROPERTIES)) //will close the stream automatically after use
        {
            properties.load(stream);
        }
        catch (IOException e)
        {
            log.error("Error loading properties file: " + DEFAULT_PROPERTIES, e);
            throw e;
        }
        return properties;
    }
}
