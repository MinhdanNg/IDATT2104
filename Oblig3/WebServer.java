import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class WebServer {
    public static void main(String[] args) throws IOException{
        final int PORTNR = 80;
        String line = "";
        ServerSocket server = new ServerSocket(PORTNR);
        Socket connection = server.accept();

        System.out.println("Web server connected!");

        PrintWriter writer = new PrintWriter(connection.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        writer.println("HTTP/1.0 200 OK");
        writer.println("Content-Type: text/html; charset=utf-8");
        writer.println("");
        writer.println("<!DOCTYPE html>");
        writer.println("<html><body>");
        writer.println("<h1> Du har koblet deg opp til min enkle web-tjener </h1>");
        writer.println("Header fra klient er:");
        writer.println("<ul>");
        line = reader.readLine();
        while(line != null){
            writer.println("<li>" + line + "</li>");
            line = reader.readLine();
        }
        writer.println("</ul>");
        writer.println("</body></html>");

        connection.close();
        writer.close();
        server.close();
        reader.close();
    }
}