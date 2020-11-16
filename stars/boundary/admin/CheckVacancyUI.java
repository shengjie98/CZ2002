package stars.boundary.admin;

import java.util.ArrayList;
import stars.controller.*;
import stars.entity.*;
import stars.boundary.*;
import stars.controller.AdminController;

public class CheckVacancyUI extends SelectUI {
    public void checkVacancy(AdminController adminController) {
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

        System.out.printf("Vacancy is: %d\n", selectedIndex.getVacancy());
    }
}
