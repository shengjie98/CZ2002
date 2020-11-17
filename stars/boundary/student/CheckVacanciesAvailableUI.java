package stars.boundary.student;

import stars.boundary.SelectUI;
import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

/**
 * UI for student to check vacancies of an index
 */
public class CheckVacanciesAvailableUI extends SelectUI {
    /**
     * UI for student to check vacancies of an index
     * 
     * @param studentController student controller with initialised database and
     *                          student
     */
    public void checkVacanciesAvailable(StudentController studentController) {
        ArrayList<Course> courseList;
        Course selectedCourse;
        ArrayList<Index> indexList;
        Index selectedIndex;
        courseList = studentController.getCourseList();
        selectedCourse = (Course) select(courseList);
        if (selectedCourse == null) {
            System.out.println("\nNo Courses Available!\n");
            return;
        }
        indexList = selectedCourse.getIndexList();
        selectedIndex = (Index) select(indexList);
        if (selectedIndex == null) {
            System.out.println("\nNo Indexes Available!\n");
            return;
        }
        System.out.printf("Vacancies: %d\n", selectedIndex.getVacancy());
    }
}
