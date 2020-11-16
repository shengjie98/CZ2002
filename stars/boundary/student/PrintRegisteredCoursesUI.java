package stars.boundary.student;

import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

public class PrintRegisteredCoursesUI {
    public void printRegisteredCourses(StudentController studentController) {
        ArrayList<Index> indexList;
        indexList = studentController.getRegisteredIndex();
        for (Index index: indexList) {
            System.out.println("  "+ index.print());
        }
    }
}
