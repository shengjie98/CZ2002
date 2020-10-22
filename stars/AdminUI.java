package stars;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class AdminUI extends SelectUI {
    private AdminController adminController;

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
            System.out.println("5. Check availablity slot for an index");
            System.out.println("6. Print student list by index number");
            System.out.println("7. Print student list by course");
            System.out.print("Option: ");
            i = sc.nextInt();
            switch (i) {
                case 2:
                    addStudent();
                    break;
                default:
                    break;
            }

        } while (i > 0 && i < 8);
        return;
    }

    private void addStudent() {
        Scanner sc = new Scanner(System.in);
        String studentName, studentID, email, password, nationality, gender, degree, start, end;
        System.out.println("Add a Student");
        System.out.print("  a. Student Name: ");
        studentName = sc.nextLine();
        System.out.print("  b. Student ID: ");
        studentID = sc.nextLine();
        System.out.print("  c. Email: ");
        email = sc.nextLine();
        System.out.print("  d. Password: ");
        password = sc.nextLine();
        System.out.print("  e. Nationality: ");
        nationality = sc.nextLine();
        System.out.print("  f. Gender: ");
        gender = sc.nextLine();
        System.out.print("  g. Degree: ");
        degree = sc.nextLine();
        System.out.print("  h. Access Perios Start (DD/MM/YYYY): ");
        start = sc.nextLine();
        System.out.print("  i. Access Perios Start (DD/MM/YYYY): ");
        end = sc.nextLine();

        // admin controller.add student(studentName, nationality, gender, studentID,
        // degree, email)
        adminController.addStudent(studentName, nationality, gender, studentID, degree, email);
        // login controller add account()
        // loginController.add
    }

    private void editCourseInformation() {
        Scanner sc = new Scanner(System.in);
        ArrayList<Course> courseList;
        Index[] indexList;
        Index selectedIndex;
        System.out.println("Edit Course Information");

        // get list of courses from the course database
        courseList = adminController.getCourseList();
        Course selectedCourse = (Course) select(courseList);
        int Au = selectedCourse.getAu();
        String CourseID = selectedCourse.getCourseID();

        System.out.println("Edit Course Information");
        System.out.println(selectedCourse.print());
        System.out.println();

    }
}
