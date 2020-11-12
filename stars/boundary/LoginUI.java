package stars.boundary;

import java.util.Scanner;
import stars.controller.*;
import stars.entity.*;


public class LoginUI {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        LoginController loginController = new LoginController();
        String username, password;
        int i;
        System.out.println("Welcome to STARS!");
        do {
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Option: ");
            i = sc.nextInt();
            switch (i) {
                case 1: 
                    login();
                    break;
                case 2: 
                    exit();
                    break;
            } while (i > 0 && i < 3);    
        } while (!exit);
    }
    private void login() {
        System.out.print("Username: ");
        username = sc.next();
        System.out.print("Password: ");
        password = new String(System.console().readPassword());
            
        if (!loginController.verifyLogin(username, password)) {
            System.out.println("Invalid Input");
        } else {
            loginController.verifyLogin(username, password);
        };        
    }

    private void exit() {
        System.out.println("Thank you for using STARS!");
        sc.close();
    }
            
        // password = sc.next();
        // loginController.verifyLogin(username, password);
}
