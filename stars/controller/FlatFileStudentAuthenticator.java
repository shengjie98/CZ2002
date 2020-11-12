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
import stars.entity.*;
import stars.boundary.*;

public class FlatFileStudentAuthenticator implements StudentAuthenticator {
    private final String STUDENT_ACCOUNTS_FILE = "stars/studentAccounts.txt";

    public boolean authenticate(String username, String password) {
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


        try{
            PrintWriter printStream = new PrintWriter(new BufferedWriter((new FileWriter(STUDENT_ACCOUNTS_FILE))));
            for (String s: lines) {
                printStream.println(s);
            }
            printStream.close();
        } catch (IOException e) {
            System.out.println("file not found error\n");
        }
    }

    public void addStudent(String username, String password, LocalDateTime accessStart, LocalDateTime accessEnd) {
        int hashedPassword = password.hashCode();
        try{
            PrintWriter printStream = new PrintWriter(new BufferedWriter((new FileWriter(STUDENT_ACCOUNTS_FILE, true))));
            printStream.println(String.join(" ", username, Integer.toString(hashedPassword), accessStart.toString(), accessEnd.toString()));
            printStream.close();
        } catch (IOException e) {
            System.out.println("file not found error\n");
        }
    }
}
