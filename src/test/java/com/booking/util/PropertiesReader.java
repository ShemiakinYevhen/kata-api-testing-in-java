package com.booking.util;

import org.junit.jupiter.params.aggregator.ArgumentAccessException;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.Properties;

public class PropertiesReader {

    private static final String PROPERTY_FILE_PATH = "/src/test/resources/application.properties";
    private static final Properties properties = new Properties();

    /**
     * Initialization of application properties using constant PROPERTY_FILE_PATH variable
     */
    static {
        try (FileInputStream input = new FileInputStream(PROPERTY_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            throw new ArgumentAccessException(String.format("Filed to load properties file using this path: %s\n%s", PROPERTY_FILE_PATH, e.getMessage()));
        }
    }

    /**
     * Returns value of property by provided key
     *
     * @param key - string value of name of the property
     * @return string variable containing value of the property
     */
    public static String getProperty(String key) {
        if (key.isBlank()) throw new InvalidParameterException(String.format("Invalid key provided: '%s'", key));
        String value = properties.getProperty(key);
        if (value.isBlank()) throw new InvalidParameterException(String.format("Invalid property value: '%s' for key '%s'", value, key));
        return value;
    }
}
