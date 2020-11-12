package stars.controller;

import java.util.ArrayList;
import stars.entity.*;
import stars.boundary.*;

public class IndexStudentSwopper {
    public void swopStudent(Index index, Student curStudent, Student newStudent) {
        ArrayList<Student> confirmed = index.getConfirmedList();
        int i;
        if (confirmed.contains(curStudent)) {
            index.dropStudentFromConfirmedList(curStudent);
            index.addStudentToConfirmedList(newStudent);
        } else {
            i = index.getWaitList().indexOf(curStudent);
            index.dropStudentFromWaitList(curStudent);
            index.insertStudentToWaitList(newStudent, i);
        }
    }
}
