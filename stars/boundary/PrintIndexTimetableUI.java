package stars.boundary;

import stars.controller.UserControllerInterface;
import java.util.ArrayList;

import stars.entity.*;

/**
 * In charge of printing out the Timings of the Index
 */
public class PrintIndexTimetableUI extends SelectUI {
    /**
     * Prints out the Timings of the Index
     * 
     * @param userControllerInterface either a StudentController or an
     *                                AdminController
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
