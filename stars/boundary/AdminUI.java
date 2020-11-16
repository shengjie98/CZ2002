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
        System.out.println("===ADMIN MENU===");
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
                    EditStudentAccessUI editStudentAccess = new EditStudentAccessUI();
                    editStudentAccess.editStudentAccess(adminController, dateFormatter);
                    break;
                case 2:
                    AddStudentUI addStudentUI = new AddStudentUI();
                    addStudentUI.addStudent(adminController, dateFormatter);
                    break;
                case 3:
                    AddCourseUI addCourseUI = new AddCourseUI();
                    addCourseUI.addCourse(adminController, timeFormatter);
                    break;
                case 4:
                    EditCourseInformationUI editCourseInformationUI = new EditCourseInformationUI();
                    editCourseInformationUI.editCourseInformation(adminController, timeFormatter);
                    break;
                case 5:
                    CheckVacancyUI checkVacancyUI = new CheckVacancyUI();
                    checkVacancyUI.checkVacancy(adminController);
                    break;
                case 6:
                    PrintStudentListByIndexUI printStudentListByIndexUI = new PrintStudentListByIndexUI();
                    printStudentListByIndexUI.printStudentListByIndex(adminController);
                    break;
                case 7:
                    PrintStudentListByCourseUI printStudentListByCourseUI = new PrintStudentListByCourseUI();
                    printStudentListByCourseUI.printStudentListByCourse(adminController);
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

    private void getallstudents() {
        ArrayList<Student> ls = adminController.getStudentList();
        System.out.println("Students: ");
        if (ls.size() == 0) {
            System.out.println("\nNo Students in Database!\n");
        }
        for (Student smth : ls) {
            System.out.printf("%s\n", smth.print());
        }
    }

    private void getallcourses() {
        ArrayList<Course> ls = adminController.getCourseList();
        System.out.println("Courses: ");
        if (ls.size() == 0) {
            System.out.println("\nNo Courses in Database!\n");
        }
        for (Course another : ls) {
            System.out.printf("%s\n", another.print());
        }
    }
}
