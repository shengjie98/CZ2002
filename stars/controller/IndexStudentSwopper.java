package stars.controller;

import java.util.ArrayList;
import stars.entity.*;

/**
 * Class to handle logic for student swopping Index with another student
 */
public class IndexStudentSwopper {
    /**
     * Handles the logic to add swop two students, one who is currently registered and one that is taking the place of the other student
     * @param index Index where the swop is happening
     * @param curStudent Student object who is currently registered for the Index
     * @param newStudent new Student object that is going to take the place in the Index
     * @return true if the new Student is added to the confirmed list, false if the new student is added to the waitlist
     */
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
