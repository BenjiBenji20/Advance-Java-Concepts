import model.Config;

public class Crud {
    public static void main(String[] args) {
        try {
            Class.forName("model.Config");
        } catch (Exception e) {
            System.out.println("Error found");
            e.printStackTrace();
        }
    }
}
