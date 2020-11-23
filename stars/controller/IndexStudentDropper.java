package stars.controller;

import java.util.ArrayList;
import stars.entity.*;

/**
 * Class to handle logic for student dropping an Index 
 */
public class IndexStudentDropper {
    /**
     * Handles logic to drop Index by Student
     * 
     * @param index                    Index which student intends to drop of the
     *                                 student
     * @param studentRegisteredCourses StudentRegisteredCourses object
     * @return true if student was in the index, false if student was from the
     *         waitlist
     */
    public boolean dropStudent(Index index, StudentRegisteredCourses studentRegisteredCourses) {
        Student student = studentRegisteredCourses.getOwner();
        ArrayList<Student> confirmed = index.getConfirmedList();
        ArrayList<Student> wait = index.getWaitList();
        if (confirmed.contains(student)) {
            // System.out.println("in drop1");
            index.dropStudentFromConfirmedList(student);
            studentRegisteredCourses.removeFromConfirmedIndexArray(index);
            studentRegisteredCourses.subtractAU(index.getAU());
            // System.out.printf("wait.size() : %d", wait.size());
            if (wait.size() > 0) {
                // System.out.println("in get next in queue");
                Student newStudent = index.dequeueStudent();
                index.addStudentToConfirmedList(newStudent);
                newStudent.sendNotification(index);
                newStudent.moveToConfirmed(index);
            }
            return true;
        } else {
            // System.out.println("in drop2");
            index.dropStudentFromWaitList(student);
            studentRegisteredCourses.removeFromWaitlistedIndexArray(index);
            studentRegisteredCourses.subtractAU(index.getAU());
            return false;
        }
    }
}
