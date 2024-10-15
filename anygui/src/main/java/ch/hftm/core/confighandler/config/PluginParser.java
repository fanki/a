package ch.hftm.core.confighandler.config;

import ch.hftm.core.confighandler.plugin.Plugin;
import ch.hftm.core.confighandler.plugin.PluginFactory;
import ch.hftm.core.confighandler.plugin.PluginType;
import com.moandjiezana.toml.Toml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class PluginParser {
    private static final Logger log = LoggerFactory.getLogger(PluginParser.class);
    private final String configFilePath;
    private final static String DEFAULT_CONFIG_FILE_PATH = "anygui_config.toml";
    private Toml toml;

    public PluginParser(String configFilePath) {
        this.configFilePath = configFilePath;
    }

    public PluginRegistry parsePlugins() {
        PluginRegistry pluginRegistry = PluginRegistry.getInstance();
        toml = getToml();

        // Parse and create Application plugin
        try {
            Map<String, Object> applicationConfig = toml.getTable(ConfigKeysConstants.PLUGIN_TYPE_APPLICATION).toMap();
            Plugin applicationPlugin = PluginFactory.createPlugin(PluginType.APPLICATION, null, toml.getTable(ConfigKeysConstants.PLUGIN_TYPE_APPLICATION));
            pluginRegistry.registerPlugin(applicationPlugin);
            log.info("Application plugin successfully registered.");
        } catch (Exception e) {
            log.error("Failed to parse Application plugin configuration.", e);
        }

        // Parse and create IO plugin
        try {
            Map<String, Object> ioConfig = toml.getTable(ConfigKeysConstants.PLUGIN_TYPE_IO).toMap();
            String ioType = (String) ioConfig.get(ConfigKeysConstants.PLUGIN_TYPE);
            Plugin ioPlugin = PluginFactory.createPlugin(PluginType.IO, ioType, toml.getTable(ConfigKeysConstants.PLUGIN_TYPE_IO));
            pluginRegistry.registerPlugin(ioPlugin);
            log.info("IO plugin of type {} successfully registered.", ioType);
        } catch (Exception e) {
            log.error("Failed to parse IO plugin configuration.", e);
        }

        // Parse and create Format plugin
        try {
            Map<String, Object> formatConfig = toml.getTable(ConfigKeysConstants.PLUGIN_TYPE_FORMAT).toMap();
            String formatType = (String) formatConfig.get(ConfigKeysConstants.PLUGIN_TYPE);
            Plugin formatPlugin = PluginFactory.createPlugin(PluginType.FORMAT, formatType, toml.getTable(ConfigKeysConstants.PLUGIN_TYPE_FORMAT));
            pluginRegistry.registerPlugin(formatPlugin);
            log.info("Format plugin of type {} successfully registered.", formatType);
        } catch (Exception e) {
            log.error("Failed to parse Format plugin configuration.", e);
        }

        // Parse and create Widget plugins
        List<Toml> widgetConfigs = toml.getTables(ConfigKeysConstants.PLUGIN_TYPE_WIDGETS);
        for (Toml widgetConfig : widgetConfigs) {
            try {
                String widgetType = widgetConfig.getString(ConfigKeysConstants.PLUGIN_TYPE);
                Plugin widgetPlugin = PluginFactory.createPlugin(PluginType.WIDGET, widgetType, widgetConfig);
                pluginRegistry.registerPlugin(widgetPlugin);
                log.info("Widget plugin of type {} successfully registered.", widgetType);
            } catch (Exception e) {
                log.error("Failed to parse Widget plugin configuration for a widget.", e);
            }
        }
        
        return pluginRegistry;
    }

    private Toml getToml() {
        File externalConfigFile = new File(configFilePath);
        if (externalConfigFile.exists() && externalConfigFile.isFile()) {
            log.info("Loading plugins from {}", externalConfigFile.getAbsolutePath());
            return new Toml().read(externalConfigFile);
        } else {
            log.warn("External toml config not found. Loading from resources.");

            try (InputStream resourceStream = getClass().getClassLoader().getResourceAsStream(DEFAULT_CONFIG_FILE_PATH)) {
                if (resourceStream == null) {
                    throw new IllegalArgumentException("No configuration file found in resources.");
                }
                return new Toml().read(resourceStream);
            } catch (Exception e) {
                log.error("Failed to load plugins from {}", configFilePath, e);
                throw new RuntimeException("Error loading configuration file.");
            }
        }
    }
    
}
