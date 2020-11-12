package stars.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import stars.entity.*;
import stars.entity.Timing.Day;
import stars.entity.Timing.Type;
import stars.boundary.*;

public class AdminController {

    private CourseDB courseInfo;
    private StudentDB studentInfo;

    public AdminController() {
        courseInfo = new CourseDB();
        studentInfo = new StudentDB();
    }

    public ArrayList<Course> getCourseList() {
        return this.courseInfo.getList();
    }

    public ArrayList<Student> getStudentList() {
        return this.studentInfo.getList();
    }

    /**
     * @return adds a student to an index of a course
     */
    public void addStudent(String studentName, String nationality, String gender, String studentID, String degree,
            String email, String password, LocalDateTime start, LocalDateTime end) {
        // instantiate a new student object
        Student newStudent = new Student(studentName, nationality, gender, studentID, degree, email);
        // add this student to the StudentDB
        studentInfo.addItem(newStudent);
        StudentAuthenticator studentAuthenticator = new FlatFileStudentAuthenticator();
        studentAuthenticator.addStudent(studentID, password, start, end);
    }

    public void save() {
        this.courseInfo.saveInformation();
        this.studentInfo.saveInformation();
    }

    public Index createIndex(Course selectedCourse, int newVacancy, int newIndexNumber) {
        Index newIndex = new Index(selectedCourse, newVacancy, newIndexNumber);
        return newIndex;
    }

    public Timing createTiming(Day day, Type type, LocalTime start, LocalTime end) {
        Timing newTiming = new Timing(day, type, start, end);
        return newTiming;
    }

    public Index addTiming(Index newIndex, Timing newTiming) {
        newIndex.addTiming(newTiming);
        return newIndex;
    }

    public Course createCourse(String newCourseID, int newAU, String newSchool, String newCourseName) {
        Course newCourse = new Course(newCourseID, newAU, newSchool, newCourseName);
        return newCourse;
    }

    public void addCourse(Course newCourse) {
        courseInfo.addItem(newCourse);
    }

    public Course addIndexToCourse(Course newCourse, Index index) {
        newCourse.addIndex(index);
        return newCourse;
    }

    // public void editCourseInformation(int choice) {
    // // rmb dont let them reduce vacancy past the current
    // if (choice == 1) {

    // }
    // // length of the list of confirmed students

    // }

    public void addIndex(Course selectedCourse, Index newIndex) {
        (selectedCourse.getIndexList()).add(newIndex);
        return;
    }

    /**
     * 
     * @param indexList
     * @param selectedIndex remove this index from the list of indexes
     */
    public boolean dropIndex(Course selectedCourse, Index selectedIndex) {
        ArrayList<Course> listOfCourses = courseInfo.getList();
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

        courseInfo.addItem(newCourse);
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
