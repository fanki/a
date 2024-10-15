package ch.hftm.core.webserver;

import java.util.List;

import ch.hftm.core.confighandler.config.PluginRegistry;
import ch.hftm.core.confighandler.plugin.widget.TextFieldWidgetPlugin;
import ch.hftm.core.confighandler.plugin.widget.WidgetPlugin;
import ch.hftm.core.webserver.util.NetworkUtils;
import io.javalin.Javalin;

public class Webserver {

    private static Javalin app;

    public static void start() {
        try {
            // Get the active network IP address using the utility method
            String ipAddress = NetworkUtils.getActiveIpAddress();

            if (ipAddress != null) {
                System.out.println("Server is running on IP address: " + ipAddress);
            } else {
                System.out.println("No active network IP address found.");
            }

            PluginRegistry registry = PluginRegistry.getInstance();

            // Start the Javalin server
            Javalin app = Javalin.create().start(ipAddress, registry.getApplicationPlugin().getPort());
            
            // Serve the HTML file at the root ("/")
            app.get("/", ctx -> {
                ctx.html(generateWidgetHtml());
                //// Left the code commented in. Maybe the Other Groupe needs it for their further work
                // ctx.html(new String(Webserver.class.getResourceAsStream("/index.html").readAllBytes())); // Use Webserver.class
                
            });            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to generate HTML dynamically based on the configured widgets
    private static String generateWidgetHtml() {
        PluginRegistry registry = PluginRegistry.getInstance();
        List<WidgetPlugin> widgetPlugins = registry.getWidgetPlugins();

        // Start building the HTML structure
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head><title>Widgets</title></head><body>");
        htmlBuilder.append("<h1>Widgets</h1>");

        // Iterate through each widget plugin and generate appropriate HTML
        for (WidgetPlugin widget : widgetPlugins) {
            if (widget instanceof TextFieldWidgetPlugin) {
                TextFieldWidgetPlugin textFieldWidget = (TextFieldWidgetPlugin) widget;
                htmlBuilder.append("<label>").append(textFieldWidget.getName()).append(":</label>");
                htmlBuilder.append("<input type='text' name='").append(textFieldWidget.getName()).append("'")
                            .append(" maxlength='").append(textFieldWidget.getMaxLength()).append("'")
                            .append(" value='").append(textFieldWidget.getDefaultValue()).append("'/><br/>");
            } else {
                // If a widget type is not recognized, output an error message in the HTML
                htmlBuilder.append("<p>Unknown widget type: ").append(widget.getType().getTypeName()).append("</p>");
            }
        }

        // Close the HTML structure
        htmlBuilder.append("</body></html>");
        return htmlBuilder.toString();
    }
}