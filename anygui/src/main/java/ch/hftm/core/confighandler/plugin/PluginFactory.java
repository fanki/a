package ch.hftm.core.confighandler.plugin;

import ch.hftm.core.confighandler.config.ConfigKeysConstants;
import ch.hftm.core.confighandler.plugin.application.ApplicationPlugin;
import ch.hftm.core.confighandler.plugin.format.PropertyFormatPlugin;
import ch.hftm.core.confighandler.plugin.io.FileIOPlugin;
import ch.hftm.core.confighandler.plugin.widget.TextFieldWidgetPlugin;
import com.moandjiezana.toml.Toml;

public class PluginFactory {

    // Main method for plugin creation based on the plugin type and name
    public static Plugin createPlugin(PluginType type, String pluginName, Toml config) {
        switch (type) {
            case APPLICATION:
                return new ApplicationPlugin(config);
            case WIDGET:
                return createWidgetPlugin(pluginName, config);
            case IO:
                return createIOPlugin(pluginName, config);
            case FORMAT:
                return createFormatPlugin(pluginName, config);
            default:
                throw new IllegalArgumentException("Unknown plugin type: " + type);
        }
    }

    // Private method for creating a Widget plugin based on its name
    private static Plugin createWidgetPlugin(String pluginName, Toml config) {
        switch (pluginName) {
            case ConfigKeysConstants.WIDGET_TEXT_FIELD:
                return new TextFieldWidgetPlugin(config);
            default:
                throw new IllegalArgumentException("Unknown widget plugin: " + pluginName);
        }
    }

    // Private method for creating an IO plugin based on its name
    private static Plugin createIOPlugin(String pluginName, Toml config) {
        switch (pluginName) {
            case ConfigKeysConstants.IO_FILE:
                return new FileIOPlugin(config);
            default:
                throw new IllegalArgumentException("Unknown IO plugin: " + pluginName);
        }
    }

    // Private method for creating a Format plugin based on its name
    private static Plugin createFormatPlugin(String pluginName, Toml config) {
        switch (pluginName) {
            case ConfigKeysConstants.FORMAT_PROPERTY:
                return new PropertyFormatPlugin(config);
            default:
                throw new IllegalArgumentException("Unknown format plugin: " + pluginName);
        }
    }
}
