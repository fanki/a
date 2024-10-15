package ch.hftm.core.webserver;
// ___          __              _             _ 
// | \ \        / /             (_)           | |
// | |\ \  /\  / /_ _ _ __ _ __  _ _ __   __ _| |
// | | \ \/  \/ / _` | '__| '_ \| | '_ \ / _` | |
// |_|  \  /\  / (_| | |  | | | | | | | | (_| |_|
// (_)   \/  \/ \__,_|_|  |_| |_|_|_| |_|\__, (_)
//                                        __/ |  
//                                       |___/   
// These tests do not work yet because they require the Mockito dependency that is used for creating mockup data. 
// The Mockito dependency is currently not implemented in the project. 

// import ch.hftm.core.webserver.util.NetworkUtils;
// import ch.hftm.core.webview.Test;
// import io.javalin.Javalin;

// public class WebserverTest {

//     @Test
//     public void testServerStartsWithValidIpAddress() {
//         // Mock NetworkUtils to return a valid IP address
//         String validIpAddress = "127.0.0.1";
//         Mockito.mockStatic(NetworkUtils.class).when(NetworkUtils::getActiveIpAddress).thenReturn(validIpAddress);

//         // Mock Javalin to avoid actually starting the server
//         Javalin mockJavalin = mock(Javalin.class);
//         when(mockJavalin.start(anyString(), anyInt())).thenReturn(mockJavalin);

//         try (var mockedJavalin = Mockito.mockStatic(Javalin.class)) {
//             mockedJavalin.when(Javalin::create).thenReturn(mockJavalin);

//             // Call the start method
//             Webserver.start();

//             // Verify that Javalin was started with the correct IP and port
//             verify(mockJavalin).start(validIpAddress, 7070);
//         }
//     }

//     @Test
//     public void testServerHandlesNullIpAddress() {
//         // Mock NetworkUtils to return null (no active IP address)
//         Mockito.mockStatic(NetworkUtils.class).when(NetworkUtils::getActiveIpAddress).thenReturn(null);

//         // Mock Javalin to avoid actually starting the server
//         Javalin mockJavalin = mock(Javalin.class);
//         when(mockJavalin.start(anyString(), anyInt())).thenReturn(mockJavalin);

//         try (var mockedJavalin = Mockito.mockStatic(Javalin.class)) {
//             mockedJavalin.when(Javalin::create).thenReturn(mockJavalin);

//             // Call the start method
//             Webserver.start();

//             // Verify that Javalin was not started, as there was no valid IP address
//             verify(mockJavalin, never()).start(anyString(), anyInt());
//         }
//     }

//     @Test
//     public void testServerHandlesException() {
//         // Mock NetworkUtils to throw an exception
//         Mockito.mockStatic(NetworkUtils.class).when(NetworkUtils::getActiveIpAddress).thenThrow(new RuntimeException("Network error"));

//         // Call the start method and ensure no exception propagates
//         assertDoesNotThrow(() -> Webserver.start(), "The server should handle exceptions gracefully");
//     }
// }