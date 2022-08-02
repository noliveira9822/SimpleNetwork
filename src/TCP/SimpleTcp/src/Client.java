import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public Client() throws Exception {
        Socket socket = new Socket("localhost", 2020);
        System.out.println("Connected to localhost");

        BufferedReader inSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        Scanner keyboard = new Scanner(System.in);
        String user_guess;

        while(inSocket.readLine().startsWith("Guess")){
            System.out.println("Guess a number from 1 to 10");
            user_guess = keyboard.nextLine();

            outWriter.println(user_guess);
        }

        System.out.println("You got it!");

        socket.close();
        System.out.println("Socket closed!");
    }
}
