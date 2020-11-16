package stars.controller;

import java.util.ArrayList;
import stars.entity.*;
import stars.boundary.*;

public class IndexStudentSwopper {
    public boolean swopStudent(Index index, Student curStudent, Student newStudent) {
        ArrayList<Student> confirmed = index.getConfirmedList();
        int i;
        if (confirmed.contains(curStudent)) {
            index.dropStudentFromConfirmedList(curStudent);
            curStudent.getregisteredCourses().removeFromConfirmedIndexArray(index);
            
            index.addStudentToConfirmedList(newStudent);
            newStudent.getregisteredCourses().addToConfirmedIndexArray(index);
            return true;
        } else {
            i = index.getWaitList().indexOf(curStudent);
            index.dropStudentFromWaitList(curStudent);
            curStudent.getregisteredCourses().removeFromWaitlistedIndexArray(index);
            
            index.insertStudentToWaitList(newStudent, i);
            newStudent.getregisteredCourses().addToWaitlistedIndexArray(index);
            return false;
        }
    }
}
