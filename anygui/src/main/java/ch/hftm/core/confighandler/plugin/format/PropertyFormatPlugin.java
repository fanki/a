package ch.hftm.core.confighandler.plugin.format;

import ch.hftm.core.confighandler.config.ConfigKeysConstants;
import ch.hftm.core.confighandler.plugin.io.FileIOPlugin;
import com.moandjiezana.toml.Toml;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PropertyFormatPlugin extends FormatPlugin {

    private static final Logger logger = Logger.getLogger(FileIOPlugin.class.getName());
    private String charset;

    public PropertyFormatPlugin(Toml config) {
        super();
        parseConfig(config);
    }


    @Override
    public void parseConfig(Toml config) {
        this.charset = config.getString(ConfigKeysConstants.FORMAT_CHARSET);
    }

    @Override
    public boolean validate(String config) {
        // TODO validation of charset
        throw new UnsupportedOperationException("PropertyFormatPlugin Validation Not implemented yet");
    }

    @Override
    public String toString() {
        return "PropertyFormatPlugin{}";
    }

    @Override
    public Map<String, String> sendToCore(String content) {
        Properties properties = new Properties();
        try (InputStream inputStream = new ByteArrayInputStream(content.getBytes(Charset.forName(charset)))) {
            properties.load(inputStream);

            return properties.keySet()
                    .stream()
                    .collect(Collectors.toMap(k -> (String) k, k -> properties.getProperty((String) k)));
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException while reading properties", e);
        }

        return Map.of();
    }

    @Override
    public String readFromCore(Map<String, Object> data) {
        return data.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
