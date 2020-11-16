package stars.boundary.student;

import stars.boundary.*;
import stars.controller.*;
import stars.entity.*;
import java.util.ArrayList;
import java.util.Scanner;

public class SwopIndexUI extends SelectUI{
    public void swopIndex(StudentController studentController) {
        boolean success;
        ArrayList<Index> indexList;
        Index selectedIndex;
        String username, password;
        StudentAuthenticator loginController = new FlatFileStudentAuthenticator();
        Scanner sc = new Scanner(System.in);

        indexList = studentController.getRegisteredIndex();
        selectedIndex = (Index)select(indexList);
        if (selectedIndex == null) {
            System.out.println("\nNo Indexes Available!\n");
            return;
        }
        
        System.out.print("Username: ");
        username = sc.next();
        System.out.print("Password: ");
        password = new String(System.console().readPassword());
        // password = sc.next();
        success = loginController.authenticate(username, password);
        if (success) {
            studentController.swopIndex(username, selectedIndex);
        } else {
            System.out.println("Incorrect details");
        }
    }
}
