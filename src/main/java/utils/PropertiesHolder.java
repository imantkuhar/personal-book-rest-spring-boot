package utils;

import java.io.IOException;
import java.util.Properties;

/**
 * Represents method which help you work with constants
 */
public class PropertiesHolder {

    private static final String APP_PROPERTIES_PATH = "/constants.properties";
    private static final Properties PROPERTIES = load();

    private static Properties load() {
        Properties properties = new Properties();
        try {
            properties.load(Properties.class.getResourceAsStream(APP_PROPERTIES_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * Return the value from resources by key
     *
     * @param key key for value
     * @return the value by key
     */
    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
