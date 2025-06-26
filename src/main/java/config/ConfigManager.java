package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager {
    private static Properties props = new Properties();
    static {
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Configuration file load failed", e);
        }
    }
    public static String get(String key) {
        return props.getProperty(key);
    }
}

