package stars.boundary;

import stars.controller.*;
import stars.entity.*;
import stars.boundary.admin.*;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AdminUI extends SelectUI {
    private AdminController adminController;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public AdminUI() {
        adminController = new AdminController();
    }

    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int i;
        System.out.println("in admin display menu");
        do {
            System.out.println("1. Edit student access period");
            System.out.println("2. Add student");
            System.out.println("3. Add course");
            System.out.println("4. Update course");
            System.out.println("5. Check availability slot for an index");
            System.out.println("6. Print student list by index number");
            System.out.println("7. Print student list by course");
            System.out.println("8. Print all students in database");
            System.out.println("9. Print all courses in database");
            System.out.print("Option: ");
            i = IntScanner.nextInt();
            switch (i) {
                case 1:
                    EditStudentAccess editStudentAccess = new EditStudentAccess();
                    editStudentAccess.editStudentAccess(adminController);
                    break;
                case 2:
                    addStudent();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    editCourseInformation();
                    break;
                case 5:
                    checkVacancy();
                    break;
                case 6:
                    printStudentListByIndex();
                    break;
                case 7:
                    printStudentListByCourse();
                    break;
                case 8:
                    getallstudents();
                    break;
                case 9:
                    getallcourses();
                    break;
                default:
                    break;
            }

        } while (i > 0 && i < 10);
        adminController.save();
        return;
    }

    private void editStudentAccess() {
        ArrayList<Student> studentList = this.adminController.getStudentList();
        Student selectedStudent = (Student) select(studentList);
        if (selectedStudent == null) {
            System.out.println("\nNo Students in Database!\n");
            return;
        }

        Scanner sc = new Scanner(System.in);
        LocalDateTime start, end;
        while (true) {
            try {
                System.out.print("  a. Access Period Start (DD/MM/YYYY hh:mm): ");
                start = LocalDateTime.parse(sc.nextLine(), dateFormatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }
        while (true) {
            try {
                System.out.print("  b. Access Period End (DD/MM/YYYY hh:mm): ");
                end = LocalDateTime.parse(sc.nextLine(), dateFormatter);
                if (end.isAfter(start)) {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }
        adminController.editAccess(selectedStudent.getStudentID(), start, end);
    }

    private void addStudent() {
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

    private void addIndex(Course newCourse) {
        Scanner sc = new Scanner(System.in);
        System.out.print("   a. Index Number: ");
        int newIndexNumber = IntScanner.nextInt();
        sc.nextLine();
        System.out.print("   b. Vacancy: ");
        int newVacancy = IntScanner.nextInt();
        Index newIndex = adminController.createIndex(newCourse, newVacancy, newIndexNumber);
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
                sc.nextLine();
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

    private void addCourse() {
        Scanner sc = new Scanner(System.in);
        System.out.print("  a. Course Name: ");
        String newCourseName = sc.nextLine();
        System.out.print("  b. Course ID: ");
        String newCourseID = sc.nextLine();
        System.out.print("  c. Number of AUs: ");
        int newAU = IntScanner.nextInt();
        sc.nextLine();
        System.out.print("  d. School: ");
        String newSchool = sc.nextLine();

        Course newCourse = adminController.createCourse(newCourseID, newAU, newSchool, newCourseName);

        System.out.print("  e. Number of Index: ");
        int numOfIndexes = IntScanner.nextInt();
        sc.nextLine();
        for (int a = 0; a < numOfIndexes; a++) {
            System.out.printf("For index %d\n", a + 1);
            addIndex(newCourse);
        }
        if (adminController.addCourse(newCourse)) {
            System.out.println("Course Added!");
        } else {
            System.out.println("Error adding course, course already exisits.");
        }
    }

    private void editCourseInformation() {
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
                selectedCourse.setCourseID(sc.nextLine());
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
                System.out.println("  New School name: ");
                selectedCourse.setSchool(sc.nextLine());
                break;
            }
            case 4: {
                addIndex(selectedCourse);
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

    private void checkVacancy() {
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

        ArrayList<Index> indexList = selectedCourse.getIndexList();
        // Get the user to choose the index
        Index selectedIndex = (Index) select(indexList);
        if (selectedIndex == null) {
            System.out.println("\nNo Indexes Available!\n");
            return;
        }

        System.out.printf("Vacancy is: %d\n", selectedIndex.getVacancy());
    }

    private void printStudentListByIndex() {
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

        ArrayList<Index> indexList = selectedCourse.getIndexList();
        // Get the user to choose the index
        Index selectedIndex = (Index) select(indexList);
        if (selectedIndex == null) {
            System.out.println("\nNo Indexes Available!\n");
            return;
        }

        ArrayList<Student> confirmedList = selectedIndex.getConfirmedList();
        System.out.println("Confirmed Students: ");
        for (int i = 0; i < confirmedList.size(); i++) {
            System.out.printf("%s\n", (confirmedList.get(i)).print());
        }
        System.out.println("Waitlisted Students: ");
        ArrayList<Student> waitList = selectedIndex.getWaitList();
        for (int i = 0; i < waitList.size(); i++) {
            System.out.printf("%s\n", (waitList.get(i)).print());
        }
    }

    private void printStudentListByCourse() {
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

        ArrayList<Index> indexList = selectedCourse.getIndexList();

        for (Index eachIndex : indexList) {
            ArrayList<Student> confirmedList = eachIndex.getConfirmedList();
            for (Student eachStudent : confirmedList) {
                System.out.printf("%s\n", eachStudent.print());
            }
        }
    }

    private void getallstudents() {
        ArrayList<Student> ls = adminController.getStudentList();
        if (ls.size() == 0) {
            System.out.println("\nNo Students in Database!\n");
        }
        for (Student smth : ls) {
            System.out.printf("%s\n", smth.print());
        }
    }

    private void getallcourses() {
        ArrayList<Course> ls = adminController.getCourseList();
        if (ls.size() == 0) {
            System.out.println("\nNo Courses in Database!\n");
        }
        for (Course another : ls) {
            System.out.printf("%s\n", another.print());
        }
    }
}
