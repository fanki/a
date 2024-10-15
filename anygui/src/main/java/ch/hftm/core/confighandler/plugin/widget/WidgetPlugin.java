package ch.hftm.core.confighandler.plugin.widget;

import ch.hftm.core.confighandler.plugin.Plugin;
import ch.hftm.core.confighandler.plugin.PluginType;

public abstract class WidgetPlugin extends Plugin {
    protected String name;
    protected String description;

    // Constructor that initializes the plugin with its specific type
    public WidgetPlugin(PluginType type) {
        super(type);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
