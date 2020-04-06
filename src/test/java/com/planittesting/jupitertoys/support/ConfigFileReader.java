package com.planittesting.jupitertoys.support;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
    private Properties properties;
    private final String propertyFilePath = "config.properties";

    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Config.properties not found at: " + propertyFilePath);
        }
    }

    public static void loadConfigFile() {

    }

    public String getBrowser() {
        String browser = properties.getProperty("browser").toLowerCase();
        if (browser != null) {
            return browser;
        } else {
            throw new RuntimeException("Browser is not specified in the config.properties file");
        }
    }

    public Long getImplicitlyWait() {
        String implicitlyWait = properties.getProperty("implicitlyWait");
        if (implicitlyWait != null) {
            return Long.parseLong(implicitlyWait);
        } else {
            throw new RuntimeException("ImplicitlyWait is not specified in the config.properties file");
        }
    }

    public String getUrl() {
        String url = properties.getProperty("url");
        if (url != null) {
            return url;
        } else {
            throw new RuntimeException("URL is not specified in the config.properties file");
        }
    }

    public String getUsername() {
        String username = properties.getProperty("username");
        if (username != null) {
            return username;
        } else {
            throw new RuntimeException("Username is not specified in the config.properties file");
        }
    }

    public String getPassword() {
        String password = properties.getProperty("password");
        if (password != null) {
            return password;
        } else {
            throw new RuntimeException("Password is not specified in the config.properties file");
        }
    }


}
