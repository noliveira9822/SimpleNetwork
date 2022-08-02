package UDP.simpleUdp1;

public class MainReceiver {
    public static void main(String[] args) {
        try {
            new Receiver();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
