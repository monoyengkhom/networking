import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        int port = 12345; // The port number the server will listen on
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("New client connected");
                    // Input stream to read client data
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    // Read the requested file name from client
                    String fileName = reader.readLine();
                    System.out.println("Client requested file: " + fileName);
                    fileName = "example.txt";
                    File file = new File(fileName);
                    if (file.exists() && !file.isDirectory()) {
                        // File exists, send file content
                        writer.println("File exists");
                        BufferedReader fileReader = new BufferedReader(new FileReader(file));
                        String line;
                        while ((line = fileReader.readLine()) != null) {
                            writer.println(line);
                        }
                        fileReader.close();
                    } else {
                        // File does not exist
                        writer.println("file doesnot exist on server");
                    }
                } catch (IOException ex) {
                    System.out.println("Server exception: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}