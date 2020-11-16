package stars.boundary;

import java.util.Scanner;
import stars.controller.*;
import stars.boundary.student.*;

/**
 * Displays User Interface and handles User Input for Student User
 */
public class StudentUI extends SelectUI {
    StudentController studentController;

    /**
     * Constructor for StudentUI
     * @param studentID student who logged in
     */
    public StudentUI(String studentID) {
        this.studentController = new StudentController(studentID);
    }

    /**
     * Lets student user select from menu
     */
    public void displayMenu() {
        Scanner sc = new Scanner(System.in);
        int i;
        // System.out.println("Welcome, " + studentController.getOwner().getName);
        do {
            System.out.println("===STUDENT MENU===");
            System.out.println("1. Add course index");
            System.out.println("2. Drop course index");
            System.out.println("3. Print Registered courses");
            System.out.println("4. Check vacancies available");
            System.out.println("5. Change index number of course");
            System.out.println("6. Swop index number with another student");
            System.out.println("7. Display Timetable");
            System.out.println("0. Logout");
            System.out.print("Option: ");
            i = IntScanner.nextInt();
            switch (i) {
                case 1:
                    AddIndexUI addIndexUI = new AddIndexUI();
                    addIndexUI.addIndex(studentController);
                    break;
                case 2:
                    DropIndexUI dropIndexUI = new DropIndexUI();
                    dropIndexUI.dropIndex(studentController);
                    break;
                case 3:
                    PrintRegisteredCoursesUI printRegisteredCoursesUI = new PrintRegisteredCoursesUI();
                    printRegisteredCoursesUI.printRegisteredCourses(studentController);
                    break;
                case 4:
                    CheckVacanciesAvailableUI checkVacanciesAvailableUI = new CheckVacanciesAvailableUI();
                    checkVacanciesAvailableUI.checkVacanciesAvailable(studentController);
                    break;
                case 5:
                    ChangeIndexUI changeIndexUI = new ChangeIndexUI();
                    changeIndexUI.changeIndex(studentController);
                    break;
                case 6:
                    SwopIndexUI swopIndexUI = new SwopIndexUI();
                    swopIndexUI.swopIndex(studentController);
                    break;
                case 7:
                    studentController.displayTimetable();
                    break;
                default:
                    break;
            }

        } while (i > 0 && i < 8);
        studentController.save();
        return;
    }

}