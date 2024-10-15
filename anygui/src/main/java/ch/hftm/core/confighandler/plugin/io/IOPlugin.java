package ch.hftm.core.confighandler.plugin.io;

import ch.hftm.core.confighandler.plugin.Plugin;
import ch.hftm.core.confighandler.plugin.PluginType;

import java.io.IOException;

public abstract class IOPlugin extends Plugin {

    // Constructor that sets the plugin type to IO
        public IOPlugin() {
        super(PluginType.IO);
    }
    // Method to read raw data from the source
    public abstract String readRawData() throws IOException;

    // Method to write raw data to the source
    public abstract void writeRawData(String data) throws IOException;

    // Method to validate the configuration
    @Override
    public abstract boolean validate(String config);
}
