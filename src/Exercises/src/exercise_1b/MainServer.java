package exercise_1b;

public class MainServer {
    public static void main(String[] args) {
        try {
            new Server();
        }catch (Exception exception){
            exception.printStackTrace();
        }

    }
}