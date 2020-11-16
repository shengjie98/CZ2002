package stars.controller;

import java.util.ArrayList;
import stars.entity.*;
import stars.boundary.*;

public class IndexStudentDropper {
    public boolean dropStudent(Index index, StudentRegisteredCourses studentRegisteredCourses) {
        Student student = studentRegisteredCourses.getOwner();
        ArrayList<Student> confirmed = index.getConfirmedList();
        ArrayList<Student> wait = index.getWaitList();
        if (confirmed.contains(student)) {
            // System.out.println("in drop1");
            index.dropStudentFromConfirmedList(student);
            studentRegisteredCourses.removeFromConfirmedIndexArray(index);
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
            return true;
        }
    }
}
