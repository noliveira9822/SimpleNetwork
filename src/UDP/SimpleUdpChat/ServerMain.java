package UDP.SimpleUdpChat;

import UDP.SimpleUdpChat.Server.ChatServer;

public class ServerMain {
    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            server.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
