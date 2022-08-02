package exercise_2a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public record ServerThread(Socket socket, int clientNumber, int randomNumber) implements Runnable {

    @Override
    public void run() {
        try {
            int client_number = clientNumber;
            int numberToGuess = randomNumber;
            System.out.println("Client " + client_number + " connected!");
            // I/O buffers
            BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            String userGuess;
            int userNumber = -1;

            do {
                outStream.println("Guess a number between 1 and 20: ");
                userGuess = inStream.readLine();
                try {
                    userNumber = Integer.parseInt(userGuess);
                } catch (Exception exception) {

                }

            } while (userNumber != numberToGuess);

            outStream.println("You got it! Secret number is " + numberToGuess);

            socket.close();
            System.out.println("Client " + client_number + " has disconnected.");

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
