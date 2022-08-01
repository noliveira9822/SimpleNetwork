package exercise_2b;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private int clientNumber = 1;
    private int randomNumber = 0;
    private boolean guessed = false;
    private String playerName = "";

    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(2020);
            setRandomNumber(generateRandomNumber());

            while (true) {
                Socket socket = serverSocket.accept();
                ServerThread serverThread = new ServerThread(socket, this);
                Thread thread = new Thread(serverThread);
                thread.start();
            }
        } catch (IOException | SecurityException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public int getClientNumber() {
        return clientNumber++;
    }

    private int generateRandomNumber() {
        return (int) (Math.random() * 20) + 1;
    }


    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
