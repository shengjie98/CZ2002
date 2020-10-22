package stars;


import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class LoginController {
    
    /**
     * Verifies the username and passsword of the use
     * @param username
     * @param password
     */
    public void verifyLogin(String username, String password) {
        boolean adminVerification, studentVerification;
        adminVerification = checkAdmin(username, password);
        if (adminVerification) {
            AdminUI adminUI = new AdminUI();
            adminUI.displayMenu();
        } else {
            studentVerification = checkStudent(username, password);
            if (studentVerification) {
                StudentUI studentUI = new StudentUI(username);
                studentUI.displayMenu();
            }
        } 
        return;
    }

    /**
     * Checks if the input belongs to the admin
     * @param username username
     * @param password password
     * @return boolean if the username and password belongs to the admin
     */
    private boolean checkAdmin(String username, String password) {
        int hashedPassword = password.hashCode();
        System.out.println(hashedPassword);
        String adminUsername;
        int adminPassword;
        try {
            Scanner admin = new Scanner(new File("stars/adminAccount.txt"));
            adminUsername = admin.nextLine();
            adminPassword = admin.nextInt();
            if (username.equals(adminUsername) && hashedPassword == adminPassword) {
                return true;
            } 
        } catch (FileNotFoundException e) {
            System.out.print("file not file error\n");
            return false;
        }
        return false;
    }
    
    /**
     * checks if the input belongs to any student
     * @param username
     * @param password
     * @return
     */
    public boolean checkStudent(String username, String password) {
        int hashedPassword = password.hashCode();
        String studentUsername;
        int studentPassword;
        try {
            Scanner admin = new Scanner(new File("stars/studentAccounts.txt"));
            studentUsername = admin.nextLine();
            studentPassword = admin.nextInt();
            if (username.equals(studentUsername) && hashedPassword == studentPassword) {
                return true;
            } 
        } catch (FileNotFoundException e) {
            System.out.print("file not file error\n");
            return false;
        }
        return false;
    }

    /**
     * 
     * @param studentID matric number
     * @param password password
     * @param accessStart date in DD/MM/YYYY
     * @param accessEnd
     */
    public void editAccess(String studentID, String password, String accessStart, String accessEnd) {
        return;
    }
}
