package stars.boundary.admin;

import java.util.ArrayList;
import stars.controller.*;
import stars.entity.*;
import stars.boundary.*;

/**
 * UI to print student list by index
 */
public class PrintStudentListByIndexUI extends SelectUI {
    /**
     * UI to print student list by index
     */
    public void printStudentListByIndex(AdminController adminController) {
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
        if (confirmedList.size() == 0) {
            System.out.println("NIL");
        }
        for (int i = 0; i < confirmedList.size(); i++) {
            System.out.printf("%s\n", (confirmedList.get(i)).print());
        }
        System.out.println("Waitlisted Students: ");
        ArrayList<Student> waitList = selectedIndex.getWaitList();
        if (waitList.size() == 0) {
            System.out.println("NIL");
        }
        for (int i = 0; i < waitList.size(); i++) {
            System.out.printf("%s\n", (waitList.get(i)).print());
        }
    }
}
