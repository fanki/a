package ch.hftm.core.confighandler.plugin.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.nio.file.WatchService;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import com.moandjiezana.toml.Toml;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileIOPlugin extends IOPlugin {
    private static final Logger logger = Logger.getLogger(FileIOPlugin.class.getName());
    private String filePath;
    private WatchService watchService;
    private ExecutorService executorService;

    // Constructor to take the file path from the TOML configuration
    public FileIOPlugin(Toml config) {
        // Get file path from TOML configuration
        this.filePath = config.getString("io_plugin.filepath", "src/test/resources/test_file.txt");

        // Initialize WatchService if the directory exists
        try {
            Path dir = Path.of(filePath).getParent();
            if (Files.exists(dir)) {
                watchService = FileSystems.getDefault().newWatchService();
                dir.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
                executorService = Executors.newSingleThreadExecutor();
                startWatching();
            } else {
                logger.log(Level.SEVERE, "Directory for file watching does not exist: " + dir);
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error setting up WatchService for file monitoring: " + filePath, e);
        }
    }

    private void startWatching() {
        executorService.submit(() -> {
            try {
                WatchKey key;
                while ((key = watchService.take()) != null) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.kind() == StandardWatchEventKinds.ENTRY_MODIFY) {
                            Path changed = (Path) event.context();
                            if (changed.endsWith(Path.of(filePath).getFileName())) {
                                logger.info("File " + filePath + " has been modified. Reading new data.");
                                readRawData();
                            }
                        }
                    }
                    key.reset();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.log(Level.WARNING, "File watching interrupted.");
            }
        });
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