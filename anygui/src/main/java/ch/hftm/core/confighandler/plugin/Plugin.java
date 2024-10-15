package ch.hftm.core.confighandler.plugin;

import com.moandjiezana.toml.Toml;

public abstract class Plugin {
    private final PluginType type; // Field to store the specific plugin type

    // Constructor to initialize the plugin with its type
    public Plugin(PluginType type) {
        this.type = type;
    }

    // Getter for Plugin-Typ
    public PluginType getType() {
        return type;
    }

    public abstract void parseConfig(Toml config);

    public abstract boolean validate(String config);
}
