package ch.hftm.core.webserver.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NetworkUtilsTest {

    @Test
    public void testGetActiveIpAddress() {
        String ipAddress = NetworkUtils.getActiveIpAddress();
        
        // Assert that the IP address is not null or empty
        assertNotNull(ipAddress, "The IP address should not be null");
        assertFalse(ipAddress.isEmpty(), "The IP address should not be empty");

        // Optionally, validate that the IP address looks valid (basic check)
        assertTrue(ipAddress.matches(
                "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}$"),
                "The IP address should be in valid IPv4 format");
    }
}