package ch.hftm.core.confighandler.plugin.application;

import ch.hftm.core.confighandler.config.ConfigKeysConstants;
import ch.hftm.core.confighandler.plugin.Plugin;
import ch.hftm.core.confighandler.plugin.PluginType;
import com.moandjiezana.toml.Toml;

public class ApplicationPlugin extends Plugin {
    private String title;
    private int port;

    // Constructor that sets the plugin type and parses the config
    public ApplicationPlugin(Toml config) {
        super(PluginType.APPLICATION);
        parseConfig(config);
    }

    @Override
    public void parseConfig(Toml config) {
        this.title = config.getString(ConfigKeysConstants.APPLICATION_TITLE);
        this.port = config.getLong(ConfigKeysConstants.APPLICATION_PORT).intValue();
    }

    @Override
    public boolean validate(String config) {
        throw new UnsupportedOperationException("ApplicationPlugin Validation Not implemented yet");
    }

    public String getTitle() {
        return title;
    }

    public int getPort() {
        return port;
    }

    @Override
    public String toString() {
        return "ApplicationPlugin{" +
                "title='" + title + '\'' +
                ", port=" + port +
                '}';
    }
}
