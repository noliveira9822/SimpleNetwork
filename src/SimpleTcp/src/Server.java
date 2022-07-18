import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public Server() throws IOException {
        ServerSocket serverSocket = new ServerSocket(2020);

        Socket socket = serverSocket.accept();
        System.out.println("client: " + socket);

        // I/O buffers
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        int secret_number = (int) (Math.random() * 10) + 1;
        String message;

        do {
            outStream.println("Guess a number from 1 to 10");
            message = bufferedReader.readLine();

        } while (Integer.parseInt(message) != secret_number);

        outStream.println("You got it!");

        System.out.println("You got it!");

        socket.close();
        System.out.println("Socket closed");
    }
}
