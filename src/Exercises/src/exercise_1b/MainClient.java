package exercise_1b;

public class MainClient {
    public static void main(String[] args) {
        try {
            new Client();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}