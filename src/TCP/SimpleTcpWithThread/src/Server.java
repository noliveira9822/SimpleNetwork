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
                ServerThread serverThread = new ServerThread(socket, this);
                Thread thread = new Thread(serverThread);
                thread.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getClientNumber(){
        return clientNumber++;
    }
}
