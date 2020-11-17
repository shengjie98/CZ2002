package stars.boundary.admin;

import stars.controller.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Collects data from user to add student to database
 */
public class AddStudentUI {
    /**
     * Collects data from user to add course to database
     * 
     * @param adminController AdminController with initialised database
     * @param dateFormatter   dateformatter for user input for access period
     */
    public void addStudent(AdminController adminController, DateTimeFormatter dateFormatter) {
        Scanner sc = new Scanner(System.in);
        String studentName, studentID, password, nationality, gender, degree, email;
        LocalDateTime start, end;
        System.out.println("Add a Student");
        System.out.print("  a. Student Name: ");
        studentName = sc.nextLine();
        System.out.print("  b. Student ID: ");
        studentID = sc.nextLine();
        System.out.print("  c. Password: ");
        password = sc.nextLine();
        System.out.print("  d. Nationality: ");
        nationality = sc.nextLine();
        System.out.print("  e. Gender: ");
        gender = sc.nextLine();
        System.out.print("  f. Degree: ");
        degree = sc.nextLine();
        System.out.print("  g. Email: ");
        email = sc.nextLine();
        // need to change to local time object here
        while (true) {
            try {
                System.out.print("  h. Access Period Start (DD/MM/YYYY hh:mm): ");
                start = LocalDateTime.parse(sc.nextLine(), dateFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }
        while (true) {
            try {
                System.out.print("  i. Access Period End (DD/MM/YYYY hh:mm): ");
                end = LocalDateTime.parse(sc.nextLine(), dateFormatter);
                if (end.isAfter(start)) {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }

        // admin controller.add student(studentName, nationality, gender, studentID,
        // degree, email)
        if (adminController.addStudent(studentName, nationality, gender, studentID, degree, email, password, start,
                end)) {
            System.out.println("Student Added!");
        } else {
            System.out.println("Error adding student, student already exists");
        }
    }
}
