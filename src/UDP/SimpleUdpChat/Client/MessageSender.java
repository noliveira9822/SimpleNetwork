package UDP.SimpleUdpChat.Client;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class MessageSender extends Thread {

    public static final int PORT = 2020;
    private DatagramSocket mDatagramSocket;
    private String mHostname;

    public MessageSender(DatagramSocket socket, String host) {
        mDatagramSocket = socket;
        mHostname = host;
    }

    @Override
    public void run() {
        super.run();
        boolean connected = false;
        do {
            try {
                sendMessage("Connected Successfully!");
                connected = true;
            } catch (Exception e) {
                System.out.println(e.fillInStackTrace().toString());
            }
        } while (!connected);

        while (true) {
            Scanner keyboard = new Scanner(System.in);
            String messageToSend = keyboard.nextLine();
            try {
                sendMessage(messageToSend);
            } catch (Exception e) {
                System.out.println(e.fillInStackTrace().toString());
            }
        }
    }

    private void sendMessage(@NotNull String message) throws Exception {
        byte[] buffer = message.getBytes(StandardCharsets.UTF_8);
        InetAddress addr = InetAddress.getByName(mHostname);
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length, addr, PORT);
        mDatagramSocket.send(packet);
    }
}
