package exercise_1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Locale;

public record ServerThread(Socket socket, int clientNumber) implements Runnable {

    @Override
    public void run() {
        try {
            int client_number = clientNumber;
            System.out.println("Client " + client_number + " connected!");
            // I/O buffers
            BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            String message = "";

            while(!message.equalsIgnoreCase("EXIT")){
                message = inStream.readLine();
                outStream.println(message.toUpperCase(Locale.ROOT));
            }

            socket.close();
            System.out.println("Client " + client_number + " has disconnected.");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
