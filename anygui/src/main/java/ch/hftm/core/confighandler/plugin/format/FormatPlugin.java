package ch.hftm.core.confighandler.plugin.format;

import ch.hftm.core.confighandler.plugin.Plugin;
import ch.hftm.core.confighandler.plugin.PluginType;

import java.util.Map;

public abstract class FormatPlugin extends Plugin {

    // Constructor that sets the plugin type to FORMAT
        public FormatPlugin() {
        super(PluginType.FORMAT);
    }
    abstract public Map<String, String> sendToCore(String content);

    abstract public String readFromCore(Map<String, Object> data);
}
