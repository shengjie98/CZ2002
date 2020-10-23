package stars;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
// import java.util.List;
import java.util.ArrayList;

public class AdminUI extends SelectUI {
    private AdminController adminController;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private LoginController loginController;

    public AdminUI() {
        adminController = new AdminController();
        loginController = new LoginController();
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
            System.out.println("5. Check availablity slot for an index");
            System.out.println("6. Print student list by index number");
            System.out.println("7. Print student list by course");
            System.out.print("Option: ");
            i = sc.nextInt();
            switch (i) {
                case 1:
                    editStudentAccess();
                    break;
                case 2:
                    addStudent();
                    break;
                case 4:
                    editCourseInformation();
                    break;
                default:
                    break;
            }

        } while (i > 0 && i < 8);
        return;
    }

    private void editStudentAccess() {
        ArrayList<Student> studentList = this.adminController.getStudentList();
        Student selectedStudent = (Student) select(studentList);
        Scanner sc = new Scanner(System.in);
        LocalDateTime start, end;
        while (true) {
            try {
                System.out.print("  a. Access Period Start (DD/MM/YYYY hh:mm): ");
                start = LocalDateTime.parse(sc.nextLine(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }
        while (true) {
            try {
                System.out.print("  b. Access Period End (DD/MM/YYYY hh:mm): ");
                end = LocalDateTime.parse(sc.nextLine(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }
        loginController.editAccess(selectedStudent.getStudentID(), start, end);
    }

    private void addStudent() {
        Scanner sc = new Scanner(System.in);
        String studentName, studentID, password, nationality, gender, degree;
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
        // need to change to local time object here
        while (true) {
            try {
                System.out.print("  g. Access Period Start (DD/MM/YYYY hh:mm): ");
                start = LocalDateTime.parse(sc.nextLine(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }
        while (true) {
            try {
                System.out.print("  h. Access Period End (DD/MM/YYYY hh:mm): ");
                end = LocalDateTime.parse(sc.nextLine(), formatter);
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Incorrect Format");
            }
        }

        // admin controller.add student(studentName, nationality, gender, studentID,
        // degree, email)
        adminController.addStudent(studentName, nationality, gender, studentID, degree);
        loginController.addStudent(studentID, password, start, end);
    }

    private void editCourseInformation() {
        System.out.println("Edit Course Information");

        // get list of courses from the course database
        ArrayList<Course> courseList;
        courseList = adminController.getCourseList();
        // this will print out the list of courses and allow the user to select
        // the course they want to edit
        Course selectedCourse = (Course) select(courseList);

        // get user to choose the course information to edit
        System.out.println("Which part of the course do you want to edit?: \n");
        System.out.println("1: Course Code\n");
        System.out.println("2: School\n");
        System.out.println("3: Add an Index\n");
        System.out.println("4: Drop Index Number\n");
        System.out.println("5: Change vacancy of the Index\n");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i) {
            case 1:
                // Change course ID
                Scanner newCourseID = new Scanner(System.in);
                selectedCourse.setCourseID(newCourseID.nextLine());
                break;
            case 2:
                // Change school
                Scanner newSchool = new Scanner(System.in);
                selectedCourse.setSchool(newSchool.nextLine());
                break;
            case 3:
                // Add a new index into an existing course
                Scanner vacancy = new Scanner(System.in);

                // create list of timings
                // Index newIndex = new Index(selectedCourse, timings, vacancy, waitList,
                // confirmedList, indexNumber);
                // adminController.addIndex(newIndex);
                break;
            case 4:
                // Drop Index
                // get the list of indexes from the course object
                ArrayList<Index> indexList = selectedCourse.getIndexList();
                // this will print out the list of indexes and allow the user to select
                // the index that they want to edit
                Index selectedIndex = (Index) select(indexList);

                adminController.dropIndex(indexList, selectedIndex);
                break;
            case 5:
                // Change vacancy
                break;
            default:
                break;
        }
        // let user decide which of the course information to edit

    }
}
