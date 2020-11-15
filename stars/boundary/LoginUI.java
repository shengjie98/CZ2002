package stars.boundary;

import java.util.Scanner;
import stars.controller.*;
import stars.entity.*;


public class LoginUI {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
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
                    System.out.println("Thank you for using STARS!");
                    break;
            } while (i < 1 || i > 2);    
        } while (i < 2);
    }
    private static void login() {
        LoginController loginController = new LoginController();
        String username, password;
        Scanner sc = new Scanner(System.in);
        System.out.print("Username: ");
        username = sc.next();
        System.out.print("Password: ");
        password = new String(System.console().readPassword());
        // password = sc.next();
        if (!loginController.verifyLogin(username, password)) {
            System.out.println("Invalid Input");
        }        
    }



}
