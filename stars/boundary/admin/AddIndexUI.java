package stars.boundary.admin;

import stars.controller.AdminController;

import stars.entity.*;
import stars.boundary.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Collects data to add index to course
 */
public class AddIndexUI {
    /**
     * Collects data to add index to course
     * 
     * @param adminController AdminController with database intitialised
     * @param newCourse       Course for which index will be added to
     * @param timeFormatter   timeformatter for timing inputs
     */
    public void addIndex(AdminController adminController, Course newCourse, DateTimeFormatter timeFormatter) {
        Scanner sc = new Scanner(System.in);
        System.out.print("   a. Index Number: ");
        int newIndexNumber = IntScanner.nextInt();
        System.out.print("   b. Vacancy: ");
        int newVacancy = IntScanner.nextInt();
        Index newIndex = adminController.createIndex(newCourse, newVacancy, newIndexNumber);
        IndexTimingDisplayer indexTimingDisplayer = new IndexTimingDisplayer(newIndex);
        System.out.println("   c. Timings: ");
        int choice;
        while (true) {
            // get the type
            Timing.Type newType = Timing.Type.LEC; // !!This is not supposed to be the default; change this
            System.out.println("    a. Type: ");
            do {
                System.out.println("     1. Lecture");
                System.out.println("     2. Tutorial");
                System.out.println("     3. Lab");
                System.out.print("Option: ");
                choice = IntScanner.nextInt();
            } while (choice <= 0 || choice > 3);
            switch (choice) {
                case 1:
                    newType = Timing.Type.LEC;
                    break;
                case 2:
                    newType = Timing.Type.TUT;
                    break;
                case 3:
                    newType = Timing.Type.LAB;
                    break;
            }

            // get the day of the timing
            Timing.Day newDay = Timing.Day.MON; // !!This is not supposed to be the default; change this before
            System.out.println("    b. Day: ");
            do {
                System.out.println("     1. Monday");
                System.out.println("     2. Tuesday");
                System.out.println("     3. Wednesday");
                System.out.println("     4. Thursday");
                System.out.println("     5. Friday");
                System.out.print("Option: ");
                choice = IntScanner.nextInt();
            } while (choice <= 0 || choice > 5);
            switch (choice) {
                case 1:
                    newDay = Timing.Day.MON;
                    break;
                case 2:
                    newDay = Timing.Day.TUE;
                    break;
                case 3:
                    newDay = Timing.Day.WED;
                    break;
                case 4:
                    newDay = Timing.Day.THU;
                    break;
                case 5:
                    newDay = Timing.Day.FRI;
                    break;
            }

            LocalTime start, end;
            // get the start time
            while (true) {
                try {
                    System.out.print("    c. Start Time (hh:mm): ");
                    start = LocalTime.parse(sc.nextLine(), timeFormatter);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Incorrect Format");
                }
            }
            // get the end time
            while (true) {
                try {
                    System.out.print("    d. End Time (hh:mm): ");
                    end = LocalTime.parse(sc.nextLine(), timeFormatter);
                    if (end.isAfter(start)) {
                        break;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Incorrect Format");
                }
            }

            // yet to convert to enum so the constructor is throwing an error
            Timing newTiming = adminController.createTiming(newDay, newType, start, end);
            if (adminController.addTiming(newIndex, newTiming)) {
                System.out.println("Timing Added!");
            } else {
                System.out.println("Timing could not be added! There was a clash in timing.");
            }
            indexTimingDisplayer.displayTimetable();
            System.out.print("Add another timing (Y/N)? ");
            if (!(sc.nextLine()).equals("Y")) {
                break;
            }
        }
        if (adminController.addIndex(newCourse, newIndex)) {
            System.out.println("Index Added!");
        } else {
            System.out.println("Index Could not be added! Duplicate Index ID.");
        }
    }
}
