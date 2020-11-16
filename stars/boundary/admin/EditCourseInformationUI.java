package stars.boundary.admin;

import stars.controller.*;

import stars.entity.*;
import stars.boundary.*;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class EditCourseInformationUI extends SelectUI {
    public void editCourseInformation(AdminController adminController, DateTimeFormatter timeFormatter) {
        System.out.println("Edit Course Information");

        // get list of courses from the course database
        ArrayList<Course> courseList;
        courseList = adminController.getCourseList();
        // this will print out the list of courses and allow the user to select
        // the course they want to edit
        Course selectedCourse = (Course) select(courseList);
        if (selectedCourse == null) {
            System.out.println("\nNo Courses Available!\n");
            return;
        }
        // get user to choose the course information to edit
        System.out.println("Which part of the course do you want to edit?:");
        System.out.println("  1: Course Code");
        System.out.println("  2: Course Name");
        System.out.println("  3: School");
        System.out.println("  4: Add an Index");
        System.out.println("  5: Drop Index Number");
        System.out.println("  6: Change Index ID");
        System.out.println("  7: Change vacancy of the Index");
        System.out.print("Option: ");
        Scanner sc = new Scanner(System.in);
        int i = IntScanner.nextInt();
        switch (i) {
            case 1: {
                /**
                 * Change course ID attribute
                 */
                System.out.print("  New Course ID: ");
                if (adminController.setCourseID(selectedCourse, sc.nextLine())) {
                    System.out.println("New Course ID has been set!");
                } else {
                    System.out.println("\nError! Course ID already exists.\n");
                }
                break;
            }
            case 2: {
                /**
                 * Change school attribute
                 */
                System.out.print("  New Course name: ");
                selectedCourse.setCourseName(sc.nextLine());
                break;
            }
            case 3: {
                /**
                 * Change school attribute
                 */
                System.out.print("  New School name: ");
                selectedCourse.setSchool(sc.nextLine());
                break;
            }
            case 4: {
                AddIndexUI addIndexUI = new AddIndexUI();
                addIndexUI.addIndex(adminController, selectedCourse, timeFormatter);
                break;
            }
            case 5: {
                /**
                 * Drop Index
                 */
                // get the list of indexes from the course object
                ArrayList<Index> indexList = selectedCourse.getIndexList();
                // this will print out the list of indexes and allow the user to select
                // the index that they want to drop; The admin will only
                // be allowed to drop it if it has no students
                Index selectedIndex = (Index) select(indexList);
                if (selectedIndex == null) {
                    System.out.println("\nNo Indexes Available!\n");
                    break;
                }
                if (adminController.dropIndex(selectedCourse, selectedIndex)) {
                    System.out.println("Index Dropped.");
                } else {
                    System.out.println("Unable to drop Index. Students are already in the Index!");
                }
                break;
            }
            case 6: {
                /**
                 * Change Index ID
                 */
                // get the list of indexes from the course object
                ArrayList<Index> indexList = selectedCourse.getIndexList();
                // this will print out the list of indexes and allow the user to select
                // the index that they want to drop; The admin will only
                // be allowed to drop it if it has no students
                Index selectedIndex = (Index) select(indexList);

                if (selectedIndex == null) {
                    System.out.println("\nNo Indexes Available!\n");
                    break;
                }

                System.out.print("  New Index ID: ");
                int newIndexID = IntScanner.nextInt();
                if (adminController.changeIndexID(selectedIndex, newIndexID)) {
                    System.out.println("Index ID Changed.");
                } else {
                    System.out.println("Unable to change Index ID. An Index with the same ID already exists!");
                }
                break;
            }
            case 7: {
                /**
                 * Change vacancy limit attribute of course
                 */
                ArrayList<Index> indexList = selectedCourse.getIndexList();
                // Get the user to choose the index
                Index selectedIndex = (Index) select(indexList);

                if (selectedIndex == null) {
                    System.out.println("\nNo Indexes Available!\n");
                    break;
                }

                // Get the user to input the vacancy which must be
                // greater than the current length of the list of
                // students
                int newVacancy;
                do {
                    System.out.print("  New Vacancy of Index: ");
                    // !! add while loop to force user to enter positive value
                    newVacancy = IntScanner.nextInt();
                    if (newVacancy < 0) {
                        System.out.println("Please try again! Your new vacancy cannot be negative!");
                    }
                } while (newVacancy < 0);
                if (adminController.setVacancyLimit(selectedIndex, newVacancy) == false) {
                    System.out.println("Unable to set new vacancy. Students will be kicked out of the list!");
                }
                break;
            }
            default:
                break;
        }
    }
}
