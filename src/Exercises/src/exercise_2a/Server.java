package exercise_2a;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int clientNumber = 1;

    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(2020);

            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, getClientNumber(), generateRandomNumber());
                Thread thread = new Thread(serverThread);
                thread.start();
            }
        } catch (IOException | SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private int getClientNumber() {
        return clientNumber++;
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 20) + 1;
    }
}
