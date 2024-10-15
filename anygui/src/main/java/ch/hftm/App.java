package ch.hftm;

import ch.hftm.core.confighandler.config.PluginParser;
import ch.hftm.core.confighandler.config.PluginRegistry;
import ch.hftm.core.confighandler.plugin.Plugin;
import ch.hftm.core.webserver.Webserver;
import ch.hftm.core.webview.Webview;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App{
    private static final Logger log = LoggerFactory.getLogger(PluginParser.class);

    public static void main(String[] args) {
        // retrieve configFilePath from ars
        String configFilePath = "";
        if (args.length == 1) {
            configFilePath = args[0];
        }

        // initiating plugin registry by parsing the toml file
        App.retrievePluginRegistry(configFilePath);

        // Start the Javalin webserver in a separate thread
        new Thread(Webserver::start).start();

        // Start the JavaFX webview in a separate thread
        Webview.launchWebview(args);
    }

    private static void retrievePluginRegistry(String configFilePath) {
        PluginParser pluginParser = new PluginParser(configFilePath);
        PluginRegistry registry = pluginParser.parsePlugins();

        //TODO Validate plugin configuration
        for (Plugin plugin : registry.getAllPlugins()) {
            log.info("Plugin created: {}", plugin);
        }
    }
}
