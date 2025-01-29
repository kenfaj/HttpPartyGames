
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        int port = 8080;
        String localIP = "0.0.0.0"; // Bind to all network interfaces

        HttpServer server = HttpServer.create(new InetSocketAddress(localIP, port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // Use default executor
        server.start();

        System.out.println("Server started at http://" + localIP + ":" + port);
    }

    static class MyHandler implements HttpHandler {

        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello from Java HTTP Server!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
