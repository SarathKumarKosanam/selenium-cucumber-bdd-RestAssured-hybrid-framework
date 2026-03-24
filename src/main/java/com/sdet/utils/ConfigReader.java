package com.sdet.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    private static final Dotenv dotenv;

    static {
        // Load config.properties
        try {
            FileInputStream fis = new FileInputStream(
                    "src/test/resources/config.properties"
            );
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("config.properties not found: " + e.getMessage());
        }

        // Load .env for local, fallback to system env for CI
        dotenv = Dotenv.configure().ignoreIfMissing().load();
    }

    // For non-secret config values
    public static String get(String key) {
        return properties.getProperty(key);
    }

    // For secrets — reads .env locally, GitHub Secrets in CI
    public static String getSecret(String key) {
        String value = dotenv.get(key, null);
        if (value == null) {
            value = System.getenv(key);
        }
        if (value == null) {
            throw new RuntimeException("Secret not found: " + key +
                    ". Add it to .env locally or GitHub Secrets in CI.");
        }
        return value;
    }
}