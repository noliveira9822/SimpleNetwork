package UDP.SimpleUdpChat.Client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class MessageReceiver extends Thread {

    DatagramSocket mDatagramSocket;

    private byte[] mBuffer;

    public MessageReceiver(DatagramSocket socket) {
        mDatagramSocket = socket;
        mBuffer = new byte[1024];
    }

    @Override
    public void run() {

        while (true) {
            try {
                DatagramPacket packet = new DatagramPacket(mBuffer, mBuffer.length);
                mDatagramSocket.receive(packet);
                String receivedMessage = new String(packet.getData(), 0, packet.getLength()).trim();
                System.out.println("Received: " + receivedMessage);
            } catch (IOException e) {
                System.out.println(e.fillInStackTrace().toString());
            }
        }
    }
}
