package stars.controller;

import stars.entity.*;
import stars.boundary.*;

public class IndexStudentAdder {
    public boolean addStudent(Index index, StudentRegisteredCourses studentRegisteredCourses) {
        Student student = studentRegisteredCourses.getOwner();
        // check AU limit
        int studentAU = studentRegisteredCourses.getAU();
        int indexAU = index.getAU();
        if (studentAU + indexAU > 21) {
            return false;
        }
        // check clash
        TimetableClashChecker clashChecker = new TimetableClashChecker();
        boolean allowed = clashChecker.checkClash(studentRegisteredCourses, index);
        if (!allowed) {
            return false;
        }
        // check if there is space
        int vacancy = index.getVacancy();
        if (vacancy > 0) {
            index.addStudentToConfirmedList(student);
            studentRegisteredCourses.addToConfirmedIndexArray(index);
            return true;
        } else {
            index.addStudentToWaitList(student);
            studentRegisteredCourses.addToWaitlistedIndexArray(index);
            return true;
        }
    }
}
