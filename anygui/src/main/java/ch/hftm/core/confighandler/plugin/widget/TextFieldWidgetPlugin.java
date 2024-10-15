package ch.hftm.core.confighandler.plugin.widget;

import ch.hftm.core.confighandler.config.ConfigKeysConstants;
import ch.hftm.core.confighandler.plugin.PluginType;
import com.moandjiezana.toml.Toml;

public class TextFieldWidgetPlugin extends WidgetPlugin {
    private int maxLength;
    private String defaultValue;

    public TextFieldWidgetPlugin(Toml config) {
        super(PluginType.WIDGET); // Initialize with the specific PluginType
        parseConfig(config);
    }

    @Override
    public void parseConfig(Toml config) {
        this.name = config.getString(ConfigKeysConstants.WIDGET_NAME);
        this.description = config.getString(ConfigKeysConstants.WIDGET_DESCRIPTION);
        this.defaultValue = config.getString(ConfigKeysConstants.WIDGET_DEFAULT_VALUE);
        this.maxLength = config.getLong(ConfigKeysConstants.WIDGET_TEXT_FIELD_MAX_LENGTH, 50L).intValue();
    }

    @Override
    public boolean validate(String config) {
        throw new UnsupportedOperationException("TextFieldWidgetPlugin Validation Not implemented yet");
    }

    public int getMaxLength() {
        return maxLength;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    @Override
    public String toString() {
        return "TextFieldWidgetPlugin{" +
                "defaultValue='" + defaultValue + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", maxLength=" + maxLength +
                '}';
    }
}
