import java.io.*;
import java.net.*;

public class SimpleInterestServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Server is listening on port 12345");
            
            while (true) {
                try (Socket socket = serverSocket.accept();
                     DataInputStream input = new DataInputStream(socket.getInputStream());
                     DataOutputStream output = new DataOutputStream(socket.getOutputStream())) {

                    System.out.println("New client connected");

                    double principal = input.readDouble();
                    double rate = input.readDouble();
                    double years = input.readDouble();

                    double simpleInterest = (principal * rate * years) / 100;

                    output.writeDouble(simpleInterest);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
