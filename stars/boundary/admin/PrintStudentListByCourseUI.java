package stars.boundary.admin;

import java.util.ArrayList;
import stars.controller.*;
import stars.entity.*;
import stars.boundary.*;

/**
 * UI to print student list by course
 */
public class PrintStudentListByCourseUI extends SelectUI {
    /**
     * UI to print student list by course
     * 
     * @param adminController admin controller with database intiialised
     */
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
        if (indexList.size() == 0) {
            System.out.println("\nNo Indexes Available!\n");
        }
        for (Index eachIndex : indexList) {
            System.out.println("Index " + String.valueOf(eachIndex.getIndexNumber() + ": "));
            ArrayList<Student> confirmedList = eachIndex.getConfirmedList();
            if (confirmedList.size() == 0) {
                System.out.println("NIL");
            }
            for (Student eachStudent : confirmedList) {
                System.out.printf("%s\n", eachStudent.print());
            }
        }

    }
}
