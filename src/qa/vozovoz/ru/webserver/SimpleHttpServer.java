package qa.vozovoz.ru.webserver;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;

/*
 * a simple static http server
*/
public class SimpleHttpServer {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {
            t.getRequestBody();


            InputStream instreamVoz =  t.getRequestBody();

            BufferedReader readerVoz = new BufferedReader(new InputStreamReader(instreamVoz, "UTF-8"));
            StringBuilder sbVoz = new StringBuilder();

            String lineVoz = "";
            try {
                while ((lineVoz = readerVoz.readLine()) != null) {
                    sbVoz.append(lineVoz + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    instreamVoz.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            String ssDL = sbVoz.toString();
            System.out.println("RESPONSE: " + ssDL);

            String response = "Welcome Real's HowTo test page";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}