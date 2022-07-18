import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread implements Runnable {

    private final Socket socket;
    private final Server server;

    public ServerThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            int client_number = server.getClientNumber();
            System.out.println("Client " + client_number + " connected!");
            // I/O buffers
            BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            outStream.println("Welcome! You are client number " + client_number + ". What's your name?");
            String message = inStream.readLine();

            System.out.println("Client says: " + message);

            socket.close();

            System.out.println("Client " + client_number + " has disconnected.");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
