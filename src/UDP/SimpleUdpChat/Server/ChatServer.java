package UDP.SimpleUdpChat.Server;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;

public class ChatServer extends Thread {

    private static final int PORT = 2020;
    private static final int BUFFER_SIZE = 1024;

    private DatagramSocket mDatagramSocket;

    // array to store client addresses
    private ArrayList<InetAddress> mClientAddresses;

    // array to store client ports
    private ArrayList<Integer> mClientPorts;

    // array to store online clients
    private ArrayList<String> mExistingClients;

    public ChatServer() {
        try {
            mDatagramSocket = new DatagramSocket(PORT);
            System.out.println("Server running at port " + PORT);
            mClientAddresses = new ArrayList<>();
            mClientPorts = new ArrayList<>();
            mExistingClients = new ArrayList<>();
        } catch (SocketException e) {
            System.out.println(e.fillInStackTrace().toString());
        }
    }

    @Override
    public void run() {
        byte[] buffer = new byte[BUFFER_SIZE];

        while (true) {
            try {
                //clear buffer
                Arrays.fill(buffer, (byte) 0);

                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                mDatagramSocket.receive(packet);

                String message = new String(packet.getData(), 0, buffer.length).trim();

                //get client info
                InetAddress addr = packet.getAddress();
                int port = packet.getPort();

                String id = addr.toString() + "|" + port;

                if (!mExistingClients.contains(id)) {
                    mExistingClients.add(id);
                    mClientPorts.add(port);
                    mClientAddresses.add(addr);
                }

                String messageReceived = id + " : " + message;
                System.out.println(messageReceived);

                // for each connected client, forward the received message
                byte[] data = messageReceived.getBytes(StandardCharsets.UTF_8);

                for (int i = 0; i < mClientAddresses.size(); i++) {
                    int clientPort = mClientPorts.get(i);
                    if (port != clientPort) {
                        InetAddress clientAddr = mClientAddresses.get(i);
                        packet = new DatagramPacket(data, data.length, clientAddr, clientPort);
                        mDatagramSocket.send(packet);
                    }
                }

            } catch (Exception e) {
                System.out.println(e.fillInStackTrace().toString());
            }
        }
    }
}
