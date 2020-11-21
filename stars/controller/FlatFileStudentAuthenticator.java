package stars.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import stars.exceptions.*;

/**
 * Concrete implementation of Student authenticator
 */
public class FlatFileStudentAuthenticator implements StudentAuthenticator, Authenticator {
    private final String STUDENT_ACCOUNTS_FILE = "stars/studentAccounts.txt";

    /**
     * Authenticates username and password by user with username and hashed password
     * stored in flat file
     * 
     * @param username Username to be authenticated
     * @param password Password to be authenticated
     * @return true if authentication is successful, false if incorrect username and
     *         password is input
     */
    public boolean authenticate(String username, String password) throws InvalidAccessPeriodException {
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
                if (username.equals(studentUsername) && hashedPassword == studentPassword){
                    if (now.isAfter(accessStart) && now.isBefore(accessEnd)) {
                        return true;
                    } else {
                        throw new InvalidAccessPeriodException();
                    }

                }
                
            } while (admin.hasNextLine());
        } catch (FileNotFoundException e) {
            System.out.println("file not file error\n");
            return false;
        }
        return false;
    }

    /**
     * Edit the access period of a student
     * 
     * @param username    studentID of student whose access period is being edited
     * @param accessStart start of new access period
     * @param accessEnd   end of new access period
     */
    public void editAccess(String username, LocalDateTime accessStart, LocalDateTime accessEnd) {
        String line;
        String[] lineList;
        ArrayList<String> lines = new ArrayList<String>();
        try {
            Scanner sc = new Scanner(new File(STUDENT_ACCOUNTS_FILE));
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                if (line.contains(username)) {
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

        try {
            PrintWriter printStream = new PrintWriter(new BufferedWriter((new FileWriter(STUDENT_ACCOUNTS_FILE))));
            for (String s : lines) {
                printStream.println(s);
            }
            printStream.close();
        } catch (IOException e) {
            System.out.println("file not found error\n");
        }
    }

    /**
     * Add new student login particulars to flat file
     * 
     * @param username    Username of new student
     * @param password    Password of new student
     * @param accessStart start of student access period
     * @param accessEnd   end of student access period
     */
    public void addStudent(String username, String password, LocalDateTime accessStart, LocalDateTime accessEnd) {
        int hashedPassword = password.hashCode();
        try {
            PrintWriter printStream = new PrintWriter(
                    new BufferedWriter((new FileWriter(STUDENT_ACCOUNTS_FILE, true))));
            printStream.println(String.join(" ", username, Integer.toString(hashedPassword), accessStart.toString(),
                    accessEnd.toString()));
            printStream.close();
        } catch (IOException e) {
            System.out.println("file not found error\n");
        }
    }
}
