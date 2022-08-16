package UDP.SimpleUdpChat;

import UDP.SimpleUdpChat.Client.ChatClient;

import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) {
        try {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the host name: ");
            String host = keyboard.nextLine();
            new ChatClient(host);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
