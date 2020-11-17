package stars.boundary.student;

import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

/**
 * UI to print courses and indexes registered by the student
 */
public class PrintRegisteredCoursesUI {
    /**
     * UI to print courses and indexes registered by the student
     * 
     * @param studentController student controller with initialised database and
     *                          student
     */
    public void printRegisteredCourses(StudentController studentController) {
        ArrayList<Index> indexList;
        indexList = studentController.getConfirmedIndex();
        System.out.println("Confirmed Courses");
        if (indexList.size() == 0) {
            System.out.println("NIL");
        }
        for (Index index : indexList) {
            System.out.println("  " + index.print());
        }
        indexList = studentController.getWaitlistedIndex();
        System.out.println("Waitlisted Courses");
        if (indexList.size() == 0) {
            System.out.println("NIL");
        }
        for (Index index : indexList) {
            System.out.println("  " + index.print());
        }
    }
}
