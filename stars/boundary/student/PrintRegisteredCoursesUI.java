package stars.boundary.student;

import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

public class PrintRegisteredCoursesUI {
    public void printRegisteredCourses(StudentController studentController) {
        ArrayList<Index> indexList;
        indexList = studentController.getConfirmedIndex();
        System.out.println("Confirmed Courses");
        if (indexList.size() == 0) {
            System.out.println("NIL");
        }
        for (Index index: indexList) {
            System.out.println("  "+ index.print());
        }
        indexList = studentController.getWaitlistedIndex();
        System.out.println("Waitlisted Courses");
        if (indexList.size() == 0) {
            System.out.println("NIL");
        }
        for (Index index: indexList) {
            System.out.println("  "+ index.print());
        }
    }
}
