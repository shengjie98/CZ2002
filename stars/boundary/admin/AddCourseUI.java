package stars.boundary.admin;

import stars.controller.*;

import stars.entity.*;
import stars.boundary.*;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AddCourseUI extends SelectUI {
    public void addCourse(AdminController adminController, DateTimeFormatter timeFormatter) {
        Scanner sc = new Scanner(System.in);
        System.out.print(" a. Course Name: ");
        String newCourseName = sc.nextLine();
        System.out.print(" b. Course ID: ");
        String newCourseID = sc.nextLine();
        System.out.print(" c. Number of AUs: ");
        int newAU = IntScanner.nextInt();
        System.out.print(" d. School: ");
        String newSchool = sc.nextLine();

        Course newCourse = adminController.createCourse(newCourseID, newAU, newSchool, newCourseName);

        System.out.print("How many Indexes do you want to add? ");
        int numOfIndexes = IntScanner.nextInt();
        for (int a = 0; a < numOfIndexes; a++) {
            System.out.printf("For index %d\n", a + 1);
            AddIndexUI addIndexUI = new AddIndexUI();
            addIndexUI.addIndex(adminController, newCourse, timeFormatter);
        }
        if (adminController.addCourse(newCourse)) {
            System.out.println("Course Added!");
        } else {
            System.out.println("Error adding course, course already exisits.");
        }
    }
}
