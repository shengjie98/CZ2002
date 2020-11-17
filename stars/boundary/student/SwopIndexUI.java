package stars.boundary.student;

import stars.boundary.*;
import stars.controller.*;
import stars.entity.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * UI for student to swop index with another student
 */
public class SwopIndexUI extends SelectUI {
    /**
     * UI for student to swop index with another student
     * 
     * @param studentController student controller with initialised database and
     *                          student
     */
    public void swopIndex(StudentController studentController) {
        boolean success, confirmed;
        ArrayList<Index> indexList;
        Index selectedIndex;
        String username, password;
        StudentAuthenticator loginController = new FlatFileStudentAuthenticator();
        Scanner sc = new Scanner(System.in);

        indexList = studentController.getRegisteredIndex();
        selectedIndex = (Index) select(indexList);
        if (selectedIndex == null) {
            System.out.println("\nNo Indexes Available!\n");
            return;
        }

        System.out.println("\nFriend's Login Details ");
        System.out.print("Username: ");
        username = sc.next();
        System.out.print("Password: ");
        password = new String(System.console().readPassword());
        // password = sc.next();
        success = loginController.authenticate(username, password);
        if (success) {
            try {
                confirmed = studentController.swopIndex(username, selectedIndex);
                if (confirmed) {
                    System.out.println("Swopped successfully");
                } else {
                    System.out.println("Friend in waitlist, successfully took his place in waitlist");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Incorrect details");
        }
    }
}
