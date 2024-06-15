import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {
        int port = 12345; // You can use any port number
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String userInformation;
                StringBuilder data = new StringBuilder();

                while ((userInformation = reader.readLine()) != null) {
                    data.append(userInformation).append(System.lineSeparator());
                }

                // Save data to a file
                try (FileWriter fileWriter = new FileWriter("user_data.txt", true)) {
                    fileWriter.write(data.toString());
                }

                socket.close();
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
