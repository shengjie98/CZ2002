package stars.controller;

import stars.entity.*;
import stars.exceptions.*;

/**
 * Class to handle logic for student adding a Index
 */
public class IndexStudentAdder {
    /**
     * Handles the logic to add students to Index
     * 
     * @param index                    Index where student is added to
     * @param studentRegisteredCourses StudentRegisteredCourses object of the
     *                                 student
     * @return true if added to confirmed list in the index, false if added to the
     *         waitlist of index
     * @throws ExceedAUException          If the user is unable to add the Index to
     *                                    the list of Registered Courses because it
     *                                    would exceed the AU limit
     * @throws TimetableClashException    If the user is unable to add the Index to
     *                                    the list of Registered Courses because of
     *                                    a Timetable Clash between te Index and the
     *                                    Student's exisiting Indexes
     * @throws AlreadyRegisteredException If the user is unable to add the Index
     *                                    because he has already Registered for the
     *                                    Course
     */
    public boolean addStudent(Index index, StudentRegisteredCourses studentRegisteredCourses)
            throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
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
