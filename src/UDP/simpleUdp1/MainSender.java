package UDP.simpleUdp1;

public class MainSender {
    public static void main(String[] args) {
        try {
            new Sender();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
