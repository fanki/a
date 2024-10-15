package ch.hftm.core.confighandler.plugin.io;

import java.nio.file.Files;
import java.nio.file.Path;
import com.moandjiezana.toml.Toml;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class KeyValueIOPlugin extends IOPlugin {
    private static final Logger logger = Logger.getLogger(KeyValueIOPlugin.class.getName());
    private String filePath;

    // Constructor to take the file path from the TOML configuration
    public KeyValueIOPlugin(Toml config) {
        this.filePath = config.getString("io_plugin.filePath", "default/path/to/file.properties");
    }

    // Method to read data from the file
    @Override
    public String readRawData() {
        try {
            return Files.readString(Path.of(filePath));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading the file: " + filePath, e);
            return null;
        }
    }

    // Method to write data to the file
    @Override
    public void writeRawData(String data) {
        try {
            Files.writeString(Path.of(filePath), data);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing to the file: " + filePath, e);
        }
    }

    // Method to validate if the file path is set
    @Override
    public boolean validate(String config) {
        return filePath != null && !filePath.isEmpty();
    }

    // Empty implementation because configuration parsing is done in the constructor
    @Override
    public void parseConfig(Toml config) {
        // No parsing needed here
    }
}