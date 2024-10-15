package ch.hftm.core.confighandler.plugin.format;

import ch.hftm.core.confighandler.config.ConfigKeysConstants;
import ch.hftm.core.confighandler.config.PluginRegistry;
import com.moandjiezana.toml.Toml;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Disabled
@ExtendWith(MockitoExtension.class)
public class PropertyFormatPluginTest {

//    @Mock
//    public Toml config;
//
//    @BeforeEach
//    public void setUp() {
//        Mockito.when(config.getString(ConfigKeysConstants.FORMAT_CHARSET)).thenReturn("UTF-8");
//    }
//
//    @Test
//    public void testMap_withValidProperties() {
//        // Arrange
//        PropertyFormatPlugin plugin = new PropertyFormatPlugin(config);
//        String content = "key1=value1\nkey2=value2\nkey3=value3";
//
//        // Act
//        Map<String, String> result = plugin.sendToCore(content);
//
//        // Assert
//        assertEquals(3, result.size(), "Die Map sollte 3 Einträge enthalten.");
//        assertEquals("value1", result.get("key1"));
//    }
//
//    @Test
//    public void testMap_withEmptyContent() {
//        // Arrange
//        PropertyFormatPlugin plugin = new PropertyFormatPlugin(config);
//        String content = "";
//
//        // Act
//        Map<String, String> result = plugin.sendToCore(content);
//
//        // Assert
//        assertTrue(result.isEmpty(), "Die Map sollte leer sein, wenn der Inhalt leer ist.");
//    }
//
//    @Test
//    public void testMap_withInvalidContent() {
//        // Arrange
//        PropertyFormatPlugin plugin = new PropertyFormatPlugin(config);
//        String content = "key1value1"; // Falsches Format ohne "="
//
//        // Act
//        Map<String, String> result = plugin.sendToCore(content);
//
//        // Assert
//        assertEquals(1, result.size(), "Die Map sollte einen Eintrag enthalten.");
//        assertTrue(result.containsKey("key1value1"), "Der Schlüssel 'key1value1' sollte existieren.");
//        assertEquals("", result.get("key1value1"), "Der Wert sollte leer sein, da kein '=' vorhanden ist.");
//    }

}
