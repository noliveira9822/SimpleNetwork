public class MainClient {
    public static void main(String[] args) {

        try {
            new Client();
        } catch (Exception exception) {
            exception.fillInStackTrace();
        }
    }
}
