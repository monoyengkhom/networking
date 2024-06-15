import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SimpleInterestClient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(hostname, port);
             DataInputStream input = new DataInputStream(socket.getInputStream());
             DataOutputStream output = new DataOutputStream(socket.getOutputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.print("Enter principal: ");
            double principal = scanner.nextDouble();

            System.out.print("Enter rate of interest: ");
            double rate = scanner.nextDouble();

            System.out.print("Enter number of years: ");
            double years = scanner.nextDouble();

            output.writeDouble(principal);
            output.writeDouble(rate);
            output.writeDouble(years);

            double simpleInterest = input.readDouble();
            System.out.println("The simple interest is: " + simpleInterest);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
 