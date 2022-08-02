package UDP.simpleUdp1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Receiver {

    public Receiver() throws Exception {
        DatagramSocket socket = new DatagramSocket(2020);
        Scanner keyboard = new Scanner(System.in);
        byte[] receiveArray = new byte[1500]; // MTU = 1500 (Maximum transmission unit)

        DatagramPacket packet = new DatagramPacket(receiveArray, receiveArray.length);

        socket.receive(packet);

        String message = new String(receiveArray).trim();

        System.out.println("Received: " + message);

        InetAddress senderAddress = packet.getAddress();
        int senderPort = packet.getPort();

        System.out.println("Enter your message: ");
        message = keyboard.nextLine();

        receiveArray = message.getBytes();

        packet = new DatagramPacket(receiveArray, receiveArray.length, senderAddress, senderPort);
        socket.send(packet);

        System.out.println("Sent: " + message);
    }
}
