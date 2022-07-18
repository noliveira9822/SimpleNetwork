public class MainServer {
    public static void main(String[] args) {

        try {
            new Server();
        } catch (Exception exception) {
            exception.fillInStackTrace();
        }
    }
}