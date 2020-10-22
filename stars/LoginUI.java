package stars;
import java.util.Scanner;

public class LoginUI {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        LoginController loginController = new LoginController();
        String username, password;
        boolean valid;
        do {
            System.out.print("Username: ");
            username = sc.next();
            System.out.print("Password: ");
            password = sc.next();
            valid = loginController.verifyLogin(username, password);
        } while (!valid);
    }
}
