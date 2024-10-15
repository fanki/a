package ch.hftm.core.confighandler.config;

import ch.hftm.core.confighandler.plugin.Plugin;
import ch.hftm.core.confighandler.plugin.application.ApplicationPlugin;
import ch.hftm.core.confighandler.plugin.format.FormatPlugin;
import ch.hftm.core.confighandler.plugin.io.IOPlugin;
import ch.hftm.core.confighandler.plugin.widget.WidgetPlugin;

import java.util.ArrayList;
import java.util.List;

public class PluginRegistry {
    private static PluginRegistry instance;
    private final List<Plugin> plugins;

    // Private constructor for Singleton
    private PluginRegistry() {
        plugins = new ArrayList<>();
    }

    public static PluginRegistry getInstance() {
        if (instance == null) {
            instance = new PluginRegistry();
        }
        return instance;
    }

    public void registerPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

    public List<WidgetPlugin> getWidgetPlugins() {
        List<WidgetPlugin> widgetPlugins = new ArrayList<>();
        for (Plugin plugin : plugins) {
            if (plugin instanceof WidgetPlugin) {
                widgetPlugins.add((WidgetPlugin) plugin);
            }
        }
        return widgetPlugins;
    }

    public List<FormatPlugin> getFormatPlugins() {
        List<FormatPlugin> formatPlugins = new ArrayList<>();
        for (Plugin plugin : plugins) {
            if (plugin instanceof FormatPlugin) {
                formatPlugins.add((FormatPlugin) plugin);
            }
        }
        return formatPlugins;
    }

    public List<IOPlugin> getIOPlugins() {
        List<IOPlugin> ioPlugins = new ArrayList<>();
        for (Plugin plugin : plugins) {
            if (plugin instanceof IOPlugin) {
                ioPlugins.add((IOPlugin) plugin);
            }
        }
        return ioPlugins;
    }

    public ApplicationPlugin getApplicationPlugin() {
        for (Plugin plugin : plugins) {
            if (plugin instanceof ApplicationPlugin) {
                return (ApplicationPlugin) plugin;
            }
        }
        return null;
    }

    public List<Plugin> getAllPlugins() {
        return plugins;
    }
}
