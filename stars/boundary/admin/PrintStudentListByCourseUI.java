package stars.boundary.admin;

import java.util.ArrayList;
import stars.controller.*;
import stars.entity.*;
import stars.boundary.*;

public class PrintStudentListByCourseUI extends SelectUI {
    public void printStudentListByCourse(AdminController adminController) {
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
}
