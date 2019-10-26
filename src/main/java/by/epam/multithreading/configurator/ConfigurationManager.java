package by.epam.multithreading.configurator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ConfigurationManager {
    private static final Logger LOGGER = LogManager.getLogger();

    public static String getProperty(String baseName, String key) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName);
        String value = null;
        try {
            value = resourceBundle.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.fatal("Property '" + key + "' doesn't exist.", e);
        }
        return value;
    }
}
