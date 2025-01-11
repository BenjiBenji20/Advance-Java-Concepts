import model.AddUser;
import model.DeleteUser;
import model.Show;
import model.User;

import java.util.List;
import java.util.Scanner;

public class Crud {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Action (add, show, delete): ");
        String action = input.nextLine();
        if(action.equalsIgnoreCase("Add")) {
            AddUser newUser = new AddUser();
            newUser.addUser(input);
        }
        else if(action.equalsIgnoreCase("Show")) {
            Show show = new Show();
            List<User> users = show.showUser();

            if(!users.isEmpty()) {
                for(User user : users) {
                    System.out.println("Available users: " + user.getUsername());
                }
            }
            else {
                System.out.println("No available users...");
            }
        }
        else if (action.equalsIgnoreCase("Delete")) {
            System.out.print("Enter username to delete: ");
            String username = input.nextLine().trim();
            DeleteUser deleteUser = new DeleteUser();
            deleteUser.deleteUser(username);
        }

        input.close();
    }
}
