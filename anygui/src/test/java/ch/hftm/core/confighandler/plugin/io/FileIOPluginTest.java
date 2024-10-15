package ch.hftm.core.confighandler.plugin.io;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.moandjiezana.toml.Toml;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileIOPluginTest {

    private static final String TEST_FILE_PATH = "src/test/resources/test_file.txt";

    @Test
    public void testReadRawData() throws IOException {
        // Arrange: Initialize fileIOPlugin and write data to the test file
        String tomlContent = "[io_plugin]\nfilePath = \"" + TEST_FILE_PATH + "\"";
        Toml toml = new Toml().read(tomlContent);
        FileIOPlugin fileIOPlugin = new FileIOPlugin(toml);
        Files.writeString(Path.of(TEST_FILE_PATH), "Hello, World!");

        // Act: Read the data
        String data = fileIOPlugin.readRawData();

        // Assert: Verify the content is correct
        assertEquals("Hello, World!", data);
    }

    @Test
    public void testWriteRawData() throws IOException {
        // Arrange: Initialize fileIOPlugin
        String tomlContent = "[io_plugin]\nfilePath = \"" + TEST_FILE_PATH + "\"";
        Toml toml = new Toml().read(tomlContent);
        FileIOPlugin fileIOPlugin = new FileIOPlugin(toml);

        // Act: Write data to the test file
        fileIOPlugin.writeRawData("Test Data");

        // Assert: Verify the content in the file
        String content = Files.readString(Path.of(TEST_FILE_PATH));
        assertEquals("Test Data", content);
    }

    @Test
    public void testValidate() {
        // Arrange: Initialize fileIOPlugin
        String tomlContent = "[io_plugin]\nfilePath = \"" + TEST_FILE_PATH + "\"";
        Toml toml = new Toml().read(tomlContent);
        FileIOPlugin fileIOPlugin = new FileIOPlugin(toml);

        // Act & Assert: Validate should return true since the file path is set
        assertTrue(fileIOPlugin.validate("dummy_config"));
    }
}