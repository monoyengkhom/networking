import java.io.*;
import java.net.*;

public class ClientFile {
    public static void main(String[] args) {
        String hostname = "localhost"; // The server's hostname
        int port = 12345; // The port number to connect to
        try (Socket socket = new Socket(hostname, port)) {
            // Input stream to read server response
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            // Output stream to send data to server
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            // Send the file request to the server
            String fileName = "example.txt"; // The file you want to request
            writer.println(fileName);// Read the server response
            String response;
            while ((response = reader.readLine()) != null) {
                System.out.println(response);
            }
        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}