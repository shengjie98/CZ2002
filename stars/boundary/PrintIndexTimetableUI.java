package stars.boundary;

import stars.controller.UserControllerInterface;
import java.util.ArrayList;

import stars.entity.*;

public class PrintIndexTimetableUI extends SelectUI {
    /**
     * UI for student to check vacancies of an index
     * 
     * @param studentController student controller with initialised database and
     *                          student
     */
    public void printIndexTimetable(UserControllerInterface userControllerInterface) {
        ArrayList<Course> courseList;
        Course selectedCourse;
        ArrayList<Index> indexList;
        Index selectedIndex;
        courseList = userControllerInterface.getCourseList();
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
        IndexTimingDisplayer indexTimingDisplayer = new IndexTimingDisplayer(selectedIndex);
        indexTimingDisplayer.displayTimetable();
    }
}
