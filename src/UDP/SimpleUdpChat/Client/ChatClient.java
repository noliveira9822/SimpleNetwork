package UDP.SimpleUdpChat.Client;

import java.net.DatagramSocket;
import java.net.SocketException;

public class ChatClient {

    public ChatClient(String host) {
        try {
            DatagramSocket socket = new DatagramSocket();
            MessageReceiver receiver = new MessageReceiver(socket);
            MessageSender sender = new MessageSender(socket, host);
            receiver.start();
            sender.start();
        } catch (SocketException e) {
            System.out.println(e.fillInStackTrace().toString());
        }
    }
}
