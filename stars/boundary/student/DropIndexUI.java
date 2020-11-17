package stars.boundary.student;

import stars.boundary.SelectUI;
import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

/**
 * UI for student to drop index
 */
public class DropIndexUI extends SelectUI {
    /**
     * UI for student to drop index
     * 
     * @param studentController student controller with initialised database and
     *                          student
     */
    public void dropIndex(StudentController studentController) {
        boolean success;
        ArrayList<Index> indexList;
        Index selectedIndex;
        indexList = studentController.getRegisteredIndex();
        selectedIndex = (Index) select(indexList);
        if (selectedIndex == null) {
            System.out.println("\nNo Indexes Available!\n");
            return;
        }
        success = studentController.dropIndex(selectedIndex);
        if (success) {
            System.out.println("Successfully dropped!");
        } else {
            System.out.println("Left waiting list!");
        }
    }
}
