package stars.controller;

import stars.entity.*;
import stars.exceptions.*;
import stars.boundary.*;

public class IndexStudentAdder {
    public boolean addStudent(Index index, StudentRegisteredCourses studentRegisteredCourses) throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        Student student = studentRegisteredCourses.getOwner();
        // check if student already has the course
        for (Index curIndex : studentRegisteredCourses.getIndexList()) {
            if (curIndex.getCourse().getCourseID().equals(index.getCourse().getCourseID())) {
                throw new AlreadyRegisteredException();
            }
        }
        // check AU limit
        int studentAU = studentRegisteredCourses.getAU();
        int indexAU = index.getAU();
        if (studentAU + indexAU > 21) {
            throw new ExceedAUException();
        }
        // check clash
        TimetableClashChecker clashChecker = new TimetableClashChecker();
        boolean allowed = clashChecker.checkClash(studentRegisteredCourses, index);
        if (!allowed) {
            throw new TimetableClashException();
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
            return false;
        }
    }
}
