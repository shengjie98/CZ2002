package stars.boundary.student;

import stars.boundary.SelectUI;
import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

/**
 * UI for student to add index
 */
public class ChangeIndexUI extends SelectUI {
    /**
     * UI for student to add index
     * 
     * @param studentController student controller with initialised database and
     *                          student
     */
    public void changeIndex(StudentController studentController) {
        boolean conrimed;
        ArrayList<Index> indexList;
        Index oldIndex, newIndex;

        indexList = studentController.getRegisteredIndex();
        oldIndex = (Index) select(indexList);
        if (oldIndex == null) {
            System.out.println("\nNo Indexes Available!\n");
            return;
        }
        indexList = oldIndex.getCourse().getIndexList();
        newIndex = (Index) select(indexList);
        if (newIndex == null) {
            System.out.println("\nNo Indexes Available!\n");
            return;
        }
        try {
            conrimed = studentController.changeIndex(oldIndex, newIndex);
            if (conrimed) {
                System.out.println("Successfully changed!");
            } else {
                System.out.println("New Index full! Added to waitlist!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
