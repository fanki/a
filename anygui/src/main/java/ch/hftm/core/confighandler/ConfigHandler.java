package ch.hftm.core.confighandler;

import com.moandjiezana.toml.Toml;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class ConfigHandler {
    private Toml toml;

    // Constructor that reads the config file
    public ConfigHandler() {
        String externalFilePath = "anygui_config.toml"; // The name of the file to search for
        
        File externalConfigFile = new File(externalFilePath);
        
        // First, attempt to load the external config file
        if (externalConfigFile.exists() && externalConfigFile.isFile()) {
            System.out.println("Loading configuration from external file: " + externalConfigFile.getAbsolutePath());
            toml = new Toml().read(externalConfigFile);
        } else {
            // If external file is not found, fall back to the resource folder
            System.out.println("External config not found. Loading from resources.");

            try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream("anygui_config.toml")) {
                if (resourceStream == null) {
                    throw new IllegalArgumentException("No configuration file found in resources.");
                }
                toml = new Toml().read(resourceStream);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error loading configuration file.");
            }
        }

        // Validate the config after loading
        validateConfig();
    }

    // Method to validate the configuration values
    private void validateConfig() {
        if (!toml.contains("webapp")) {
            throw new IllegalArgumentException("Missing required config section: webapp");
        }
        if (!toml.contains("gui")) {
            throw new IllegalArgumentException("Missing required config section: gui");
        }
        if (!toml.contains("format_plugins")) {
            throw new IllegalArgumentException("Missing required config section: format_plugins");
        }
        if (!toml.contains("ui_plugin")) {
            throw new IllegalArgumentException("Missing required config section: ui_plugin");
        }

        System.out.println("Configuration is valid.");
    }

    // Getter methods for configuration sections
    public Map<String, Object> getWebAppConfig() {
        return toml.getTable("webapp").toMap();
    }

    public Map<String, Object> getGuiConfig() {
        return toml.getTable("gui").toMap();
    }

    public Map<String, Object> getFormatPluginConfig() {
        return toml.getTable("format_plugins").toMap();
    }

    public Map<String, Object> getUIPluginConfig() {
        return toml.getTable("ui_plugin").toMap();
    }

    public int loadPort(Map<String, Object> webAppConfig) {
        // Attempt to read the port value from the configuration, if available
        if (webAppConfig.containsKey("port")) {
            Object portValue = webAppConfig.get("port");

            // Convert the value to an Integer, depending on the type of the value
            if (portValue instanceof Integer) {
                return (int) portValue; // If it's already an Integer
            } else if (portValue instanceof Long) {
                return ((Long) portValue).intValue(); // If it's a Long, convert to int
            } else {
                throw new IllegalArgumentException("The port value is not a valid numeric type.");
            }
        } else {
            // Fallback value if the port is not found
            System.out.println("No port found in the configuration. Using default value 8080.");
            return 8080; // Default port
        }
    }

}
