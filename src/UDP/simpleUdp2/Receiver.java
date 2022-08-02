package UDP.simpleUdp2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Receiver {

    public Receiver() throws Exception {
        DatagramSocket socket = new DatagramSocket(2020);

        while (true) {
            byte[] receiveArray = new byte[1500]; // MTU = 1500 (Maximum transmission unit)

            DatagramPacket packet = new DatagramPacket(receiveArray, receiveArray.length);

            socket.receive(packet);

            String message = new String(receiveArray).trim();

            System.out.println("Received: " + message);

            InetAddress senderAddress = packet.getAddress();
            int senderPort = packet.getPort();

            message = "OK.";

            receiveArray = message.getBytes();

            packet = new DatagramPacket(receiveArray, receiveArray.length, senderAddress, senderPort);
            socket.send(packet);

            System.out.println("Sent: " + message);
        }
    }
}
