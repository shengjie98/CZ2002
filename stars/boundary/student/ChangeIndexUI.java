package stars.boundary.student;

import stars.boundary.SelectUI;
import stars.controller.StudentController;
import stars.entity.*;
import java.util.ArrayList;

public class ChangeIndexUI extends SelectUI{
    public void changeIndex(StudentController studentController) {
        boolean success;
        ArrayList<Index> indexList;
        Index oldIndex, newIndex;
        
        indexList = studentController.getRegisteredIndex();
        oldIndex = (Index)select(indexList);
        indexList = oldIndex.getCourse().getIndexList();
        newIndex = (Index)select(indexList);
        success = studentController.changeIndex(oldIndex, newIndex);
        if (success) {
            System.out.println("Successfully changed!");
        } else {
            System.out.println("Error changing,  timetable clash");
        }
    }
}
