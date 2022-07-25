package exercise_2a;

public class MainClient {
    public static void main(String[] args) {
        try {
            new Client();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}