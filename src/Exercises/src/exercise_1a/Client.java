package exercise_1a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() throws Exception {
        Socket socket = new Socket("localhost", 2020);

        BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        Scanner keyboard = new Scanner(System.in);
        String message = "";

        System.out.println("Successfully conneced to server!");
        System.out.println("To quit, type: 'EXIT'");

        while (!message.equalsIgnoreCase("EXIT")) {
            System.out.print("Enter your text: ");

            message = keyboard.nextLine();

            outWriter.println(message);

            message = inSocket.readLine();

            System.out.println("Received text: " + message);
        }

        socket.close();
        System.out.println("Socket closed!");
    }
}
