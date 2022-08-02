package exercise_2b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public record ServerThread(Socket socket, Server serverInstance) implements Runnable {

    @Override
    public void run() {
        try {
            int client_number = serverInstance.getClientNumber();
            int numberToGuess = serverInstance.getRandomNumber();
            System.out.println("Client " + client_number + " connected!");
            // I/O buffers
            BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter outStream = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            outStream.println("Enter your name please: ");
            String user = inStream.readLine();
            String message;

            while (true) {
                outStream.println("Guess a number from 1 to 20: ");
                message = inStream.readLine();

                if ((Integer.parseInt(message) == numberToGuess) && !(serverInstance.isGuessed())) {
                    serverInstance.setGuessed(true);
                    serverInstance.setPlayerName(user);
                    outStream.println("User " + user + " has guessed the number!");
                    System.out.println("User " + user + " has guessed the number!");
                    socket.close();
                    System.out.println("Socket closed!");
                    break;
                } else if ((Integer.parseInt(message) == numberToGuess) && (serverInstance.isGuessed())) {
                    outStream.println("User " + serverInstance.getPlayerName() + " has guessed the number first!");
                    System.out.println("User " + serverInstance.getPlayerName() + " has guessed the number first!");
                    socket.close();
                    System.out.println("Socket closed!");
                    break;
                } else if (serverInstance.isGuessed()) {
                    outStream.println("User " + serverInstance.getPlayerName() + " has guessed the number!");
                    socket.close();
                    System.out.println("Socket closed!");
                    break;
                }
            }

        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
