package ch.hftm.core.webview;

import ch.hftm.core.confighandler.config.PluginRegistry;
import ch.hftm.core.confighandler.plugin.application.ApplicationPlugin;
import ch.hftm.core.webserver.util.NetworkUtils;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Webview extends Application{
    @Override
    public void start(Stage primaryStage) {
        // Create a WebView
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();

        PluginRegistry registry = PluginRegistry.getInstance();
        ApplicationPlugin applicationPlugin = registry.getApplicationPlugin();

        String ipAddress = NetworkUtils.getActiveIpAddress();
        int port = applicationPlugin.getPort();

        // Load the webpage served by the Javalin webserver
        webEngine.load("http://" + ipAddress + ":" + port);

        // Set the WebView into a VBox
        VBox root = new VBox(webView);

        // Create a Scene with the VBox
        Scene scene = new Scene(root, 800, 600);

        // Set the Scene on the Stage
        primaryStage.setTitle("JavaFX WebView - " + ipAddress + ":" + port);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Start JavaFX application in a separate thread
    public static void launchWebview(String[] args) {
        new Thread(() -> Application.launch(Webview.class, args)).start();
    }
}
