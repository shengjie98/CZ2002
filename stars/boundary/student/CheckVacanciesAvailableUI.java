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
        indexList = selectedCourse.getIndexList();
        selectedIndex = (Index)select(indexList);
        System.out.printf("Vacancies: %d\n", selectedIndex.getVacancy());
    } 
}
