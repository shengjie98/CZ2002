package stars;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;

public class LoginController {
    private final String ADMIN_ACCOUNT_FILE = "stars/adminAccount.txt";
    private final String STUDENT_ACCOUNTS_FILE = "stars/studentAccounts.txt";
    private SelectUI ui;
    /**
     * Verifies the username and passsword of the use
     * @param username
     * @param password
     */
    public void verifyLogin(String username, String password) {
        boolean adminVerification, studentVerification;
        adminVerification = checkAdmin(username, password);
        if (adminVerification) {
            this.ui = new AdminUI();
            this.ui.displayMenu();
        } else {
            studentVerification = checkStudent(username, password);
            if (studentVerification) {
                this.ui = new StudentUI(username);
                this.ui.displayMenu();
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
            Scanner admin = new Scanner(new File(ADMIN_ACCOUNT_FILE));
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
        String[] line;
        int studentPassword;
        LocalDateTime accessStart, accessEnd, now;
        try {
            Scanner admin = new Scanner(new File(STUDENT_ACCOUNTS_FILE));
            now = LocalDateTime.now();
            do {
                line = admin.nextLine().split(" ");
                studentUsername = line[0];
                studentPassword = Integer.parseInt(line[1]);
                accessStart = LocalDateTime.parse(line[2]);
                accessEnd = LocalDateTime.parse(line[3]);
                if (username.equals(studentUsername) && hashedPassword == studentPassword && now.isAfter(accessStart) && now.isBefore(accessEnd)) {
                    return true;
                } 
            } while (admin.hasNextLine());
        } catch (FileNotFoundException e) {
            System.out.println("file not file error\n");
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
    public void editAccess(String studentID, LocalDateTime accessStart, LocalDateTime accessEnd) {
        String line;
        String[] lineList;
        ArrayList<String> lines = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(new File(STUDENT_ACCOUNTS_FILE));
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.contains(studentID)) {
                    // change accessStart and access End
                    lineList = line.split(" ");
                    line = String.join(" ", lineList[0], lineList[1], accessStart.toString(), accessEnd.toString());
                    lines.add(line);
    
                } else {
                    lines.add(line);
                } 
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.print("file not found\n");
        }


        try{
            PrintWriter printStream = new PrintWriter(new BufferedWriter((new FileWriter(STUDENT_ACCOUNTS_FILE))));
            for (String s: lines) {
                printStream.println(s);
            }
            printStream.close();
        } catch (IOException e) {
            System.out.println("file not found error\n");
        }
        return;
    }

    public void addStudent(String studentID, String password, LocalDateTime accessStart, LocalDateTime accessEnd) {
        int hashedPassword = password.hashCode();
        try{
            PrintWriter printStream = new PrintWriter(new BufferedWriter((new FileWriter(STUDENT_ACCOUNTS_FILE, true))));
            printStream.println(String.join(" ", studentID, Integer.toString(hashedPassword), accessStart.toString(), accessEnd.toString()));
            printStream.close();
        } catch (IOException e) {
            System.out.println("file not found error\n");
        }
    }
}
