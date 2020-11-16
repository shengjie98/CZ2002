package stars.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import stars.entity.*;
import stars.entity.Timing.Day;
import stars.entity.Timing.Type;
import stars.boundary.*;

public class AdminController {

    private DatabaseManager dbManager;

    public AdminController() {
        dbManager = new DatabaseManager();
        dbManager.loadInformation();
    }

    public ArrayList<Course> getCourseList() {
        return this.dbManager.getCoursesArray();
    }

    public ArrayList<Student> getStudentList() {
        return this.dbManager.getStudentArray();
    }

    /**
     * @return adds a student to an index of a course
     */
    public boolean addStudent(String studentName, String nationality, String gender, String studentID, String degree,
            String email, String password, LocalDateTime start, LocalDateTime end) {
        // instantiate a new student object
        Student newStudent = new Student(studentName, nationality, gender, studentID, degree, email);
        boolean success;
        // add this student to the StudentDB
        success = dbManager.addStudent(newStudent);
        if (success) {
            StudentAuthenticator studentAuthenticator = new FlatFileStudentAuthenticator();
            studentAuthenticator.addStudent(studentID, password, start, end);
        }
        return success;
    }

    public void save() {
        this.dbManager.saveInformation();
    }

    public Index createIndex(Course selectedCourse, int newVacancy, int newIndexNumber) {
        Index newIndex = new Index(selectedCourse, newVacancy, newIndexNumber);
        return newIndex;
    }

    public Timing createTiming(Day day, Type type, LocalTime start, LocalTime end) {
        Timing newTiming = new Timing(day, type, start, end);
        return newTiming;
    }

    public boolean addTiming(Index newIndex, Timing newTiming) {
        if (newIndex.addTiming(newTiming)) {
            return true;
        }
        return false;
    }

    public Course createCourse(String newCourseID, int newAU, String newSchool, String newCourseName) {
        Course newCourse = new Course(newCourseID, newAU, newSchool, newCourseName);
        return newCourse;
    }

    public boolean addCourse(Course newCourse) {
        return dbManager.addCourse(newCourse);
    }

    public boolean setCourseID(Course selectedCourse, String newCourseID) {
        ArrayList<Course> courseList = dbManager.getCoursesArray();
        for (Course course : courseList) {
            if (newCourseID.equals(course.getCourseID())) {
                return false;
            }
        }
        selectedCourse.setCourseID(newCourseID);
        return true;
    }

    public boolean setVacancyLimit(Index selectedIndex, int newVacancyLimit) {
        // if the user tries to increase the vacancy limit
        if (newVacancyLimit > selectedIndex.getVacancyLimit()) {
            int count = 0;
            int oldVacancy = selectedIndex.getVacancy();
            // if the confirmedlist is now full
            if (selectedIndex.getVacancy() == 0) {
                // while there are still students in the waitlist
                while (selectedIndex.getWaitList().size() > 0) {
                    Student student = selectedIndex.dequeueStudent();
                    selectedIndex.addStudentToConfirmedList(student);
                    student.sendNotification(selectedIndex);
                    count++;
                }
                // if there are no more students in the waitlist and there are still
                // some empty vacancies, set the vacancy as the leftover slots
                selectedIndex.setVacancy(newVacancyLimit - oldVacancy - count);
                selectedIndex.setVacancyLimit(newVacancyLimit);
                return true;
            } else {
                // if the confirmed list is not full, simply increase the vacancy
                selectedIndex
                        .setVacancy(newVacancyLimit - (selectedIndex.getVacancyLimit() - selectedIndex.getVacancy()));
                selectedIndex.setVacancyLimit(newVacancyLimit);
                return true;
            }
            // if the user is trying to decrease the vacancy limit
        } else if (newVacancyLimit < selectedIndex.getVacancyLimit()) {
            // check if the number of students already in the list exceeds the
            // the newVacancyLimit, if so, this will not be allowed
            if ((selectedIndex.getVacancyLimit() - selectedIndex.getVacancy()) > newVacancyLimit) {
                System.out.println("here");
                return false;
            } else {
                // otherwise, set the new vacancy to be the new limit - the number of students
                // currently inside
                selectedIndex
                        .setVacancy(newVacancyLimit - (selectedIndex.getVacancyLimit() - selectedIndex.getVacancy()));
                selectedIndex.setVacancyLimit(newVacancyLimit);
                return true;
            }
        }
        // if the newVacancy is the same as the current vacancy
        return true;
    }

    public boolean addIndex(Course selectedCourse, Index newIndex) {
        if (selectedCourse.addIndex(newIndex)) {
            return true;
        }
        return false;
    }

    public boolean changeIndexID(Index selectedIndex, int newIndexID) {
        Course course = selectedIndex.getCourse();
        ArrayList<Index> indexList = course.getIndexList();
        for (Index index : indexList) {
            if (index.getIndexNumber() == newIndexID) {
                return false;
            }
        }
        selectedIndex.setIndexNumber(newIndexID);
        return true;
    }

    /**
     * 
     * @param indexList
     * @param selectedIndex remove this index from the list of indexes
     */
    public boolean dropIndex(Course selectedCourse, Index selectedIndex) {
        ArrayList<Course> listOfCourses = dbManager.getCoursesArray();
        // find the ourse in the list of courses and remove it
        for (Course eachCourse : listOfCourses) {
            if (eachCourse.equals(selectedCourse)) {
                ArrayList<Student> confirmedList = selectedIndex.getConfirmedList();
                if (confirmedList.size() == 0) {
                    (eachCourse.getIndexList()).remove(selectedIndex);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void addCourse(String courseID, int au, String school, String courseName) {
        Course newCourse = new Course(courseID, au, school, courseName);

        dbManager.addCourse(newCourse);
        return;
    }
    // private void editVacancy(Index){

    public void editAccess(String studentID, LocalDateTime start, LocalDateTime end) {
        StudentAuthenticator studentAuthenticator = new FlatFileStudentAuthenticator();
        studentAuthenticator.editAccess(studentID, start, end);
    }

    // }

    // public void editCourseAccess() {

    // }
}
