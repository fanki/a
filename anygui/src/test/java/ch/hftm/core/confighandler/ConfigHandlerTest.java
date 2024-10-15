package ch.hftm.core.confighandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class ConfigHandlerTest {
    
    private ConfigHandler configHandler;

    @BeforeEach
    void setUp() {
        // Ensure that the test configuration file is available in the resources folder
        configHandler = new ConfigHandler();
    }

    @Test
    void testLoadPortWithInteger() {
        Map<String, Object> webAppConfig = new HashMap<>();
        webAppConfig.put("port", 8080);

        int port = configHandler.loadPort(webAppConfig);
        assertEquals(8080, port);
    }

    @Test
    void testLoadPortWithLong() {
        Map<String, Object> webAppConfig = new HashMap<>();
        webAppConfig.put("port", 8080L);

        int port = configHandler.loadPort(webAppConfig);
        assertEquals(8080, port);
    }

    @Test
    void testLoadPortWithInvalidType() {
        Map<String, Object> webAppConfig = new HashMap<>();
        webAppConfig.put("port", "invalid");

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            configHandler.loadPort(webAppConfig);
        });

        assertEquals("The port value is not a valid numeric type.", thrown.getMessage());
    }

    @Test
    void testLoadPortWithMissingKey() {
        Map<String, Object> webAppConfig = new HashMap<>();

        int port = configHandler.loadPort(webAppConfig);
        assertEquals(8080, port);
    }

    @Test
    void testValidConfiguration() {
        configHandler = new ConfigHandler();
        // No assertion needed if no exception is thrown
    }
}
