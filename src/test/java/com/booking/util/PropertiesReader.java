package com.booking.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {

    private static final String PROPERTY_FILE_PATH = System.getProperty("user.dir") + "/src/test/resources/application.properties";
    private static final Properties properties = loadProperties();

    private PropertiesReader() {}

    private static Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTY_FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            throw new IllegalStateException(String.format("Filed to load properties file using this path: %s\n%s", PROPERTY_FILE_PATH, e.getMessage()));
        }
        return properties;
    }

    /**
     * Returns value of property by provided key
     *
     * @param key - string value of name of the property
     * @return string variable containing value of the property
     */
    public static String getProperty(String key) {
        validateKey(key);
        String value = properties.getProperty(key);
        validateValue(value);
        return value;
    }

    private static void validateKey(String key) {
        if (Objects.isNull(key) || key.isBlank()) {
            throw new IllegalArgumentException("Invalid key provided");
        }
    }

    private static void validateValue(String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            throw new IllegalArgumentException("Invalid property value for key '" + value + "'");
        }
    }
}
