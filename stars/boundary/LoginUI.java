package stars.boundary;

import java.util.Scanner;
import stars.controller.*;
import stars.entity.*;


public class LoginUI {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        LoginController loginController = new LoginController();
        String username, password;
        do {
            System.out.print("Username: ");
            username = sc.next();
            System.out.print("Password: ");
            password = new String(System.console().readPassword());
            // password = sc.next();
            loginController.verifyLogin(username, password);
        } while (true);
    }
}
