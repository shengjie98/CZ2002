package stars.boundary.student;

import stars.boundary.SelectUI;
import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

public class CheckVacanciesAvailableUI extends SelectUI{
    public void checkVacanciesAvailable(StudentController studentController) {
        ArrayList<Course> courseList;
        Course selectedCourse;
        ArrayList<Index> indexList;
        Index selectedIndex;
        courseList = studentController.getCourseList();
        selectedCourse = (Course)select(courseList);
        if (selectedCourse == null) {
            System.out.println("\nNo Courses Available!\n");
            return;
        }
        indexList = selectedCourse.getIndexList();
        selectedIndex = (Index)select(indexList);
        if (selectedIndex == null) {
            System.out.println("\nNo Indexes Available!\n");
            return;
        }
        System.out.printf("Vacancies: %d\n", selectedIndex.getVacancy());
    } 
}
