package stars.boundary.student;

import stars.boundary.SelectUI;
import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

public class DropIndexUI extends SelectUI {
    public void dropIndex(StudentController studentController) {
        boolean success;
        ArrayList<Index> indexList;
        Index selectedIndex;
        indexList = studentController.getRegisteredIndex();
        selectedIndex = (Index)select(indexList);
        success = studentController.dropIndex(selectedIndex);
        if (success) {
            System.out.println("Successfully dropped!");
        } else {
            System.out.println("Error dropping");
        }
    }
}
