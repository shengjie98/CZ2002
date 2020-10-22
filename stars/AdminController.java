package stars;

import java.util.List;
import java.util.ArrayList;

public class AdminController {
    private CourseDB courseInfo;
    private StudentDB studentInfo;

    public AdminController() {
        courseInfo = new CourseDB();
        studentInfo = new StudentDB();
    }

    /**
     * @return adds a student to an index of a course
     */
    public void addStudent(String studentName, String nationality, String gender, String studentID, String degree,
            String email) {
        // instantiate a new student object
        Student newStudent = new Student(studentName, nationality, gender, studentID, degree);
        // add this student to the StudentDB
        studentInfo.addStudent(newStudent);
    }

    // public void editCourseInformation(int choice) {
    // // rmb dont let them reduce vacancy past the current
    // if (choice == 1) {

    // }
    // // length of the list of confirmed students

    // }

    // private void addIndex(ArrayList<Index> indexList, Index newIndex) {

    // }

    public void dropIndex(ArrayList<Index> indexList, Index selectedIndex) {
        indexList.remove(selectedIndex);
        return;
    }

    // private void editVacancy(Index){

    // }

    public ArrayList<Course> getCourseList() {
        return this.courseInfo.getCourseList();
    }

    public void addCourse(String courseID, Index[] indexList, int au, String school, String courseName) {

    }

    public void editCourseAccess() {

    }

    public void printStudentList() {

    }
}
