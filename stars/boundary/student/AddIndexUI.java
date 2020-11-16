package stars.boundary.student;

import stars.boundary.SelectUI;
import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

/**
 * UI for student to add index
 */
public class AddIndexUI extends SelectUI {
    /**
     * UI for student to add index
     * @param studentController student controller with database initialised and student 
     */
    public void addIndex(StudentController studentController) {
        boolean confirm;
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
        try {
            confirm = studentController.addIndex(selectedIndex);
            if (confirm) {
                System.out.println("Successfully added!");
            } else {
                System.out.println("Index full, added to waitlist!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
