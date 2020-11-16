package stars.boundary.student;

import stars.boundary.SelectUI;
import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

public class AddIndexUI extends SelectUI {
    public void addIndex(StudentController studentController) {
        boolean success;
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
        success = studentController.addIndex(selectedIndex);
        if (success) {
            System.out.println("Successfully added!");
        } else {
            System.out.println("Error adding, timetable clash or too many AUs or already registered");
        }
    }
}
