package ch.hftm.core.webserver.util;

import java.net.InetAddress;
import java.net.Socket;

public class NetworkUtils {

    // Method to retrieve the active IP address
    public static String getActiveIpAddress() {
        try (Socket socket = new Socket("8.8.8.8", 53)) { // Google DNS server (no real data is sent)
            InetAddress localAddress = socket.getLocalAddress();
            return localAddress.getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}