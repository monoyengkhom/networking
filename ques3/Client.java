import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String hostname = "localhost"; // Server address
        int port = 12345; // Port number

        try (Socket socket = new Socket(hostname, port)) {

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your information (type 'exit' to finish):");

            String userInput;
            while (!(userInput = scanner.nextLine()).equalsIgnoreCase("exit")) {
                writer.println(userInput);
            }

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}
