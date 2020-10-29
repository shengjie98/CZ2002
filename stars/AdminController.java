package stars;

// import java.util.List;
import java.util.ArrayList;

public class AdminController {
    private CourseDB courseInfo;
    private StudentDB studentInfo;

    public AdminController() {
        courseInfo = new CourseDB();
        studentInfo = new StudentDB();
    }

    public ArrayList<Course> getCourseList() {
        return this.courseInfo.getCourseList();
    }

    public ArrayList<Student> getStudentList() {
        return this.studentInfo.getStudentList();
    }

    /**
     * @return adds a student to an index of a course
     */
    public void addStudent(String studentName, String nationality, String gender, String studentID, String degree) {
        // instantiate a new student object
        Student newStudent = new Student(studentName, nationality, gender, studentID, degree);
        // add this student to the StudentDB
        studentInfo.addStudent(newStudent);
    }

    public void save() {
        this.courseInfo.saveInformation();
        this.studentInfo.saveInformation();
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
    public void dropIndex(ArrayList<Index> indexList, Index selectedIndex) {
        indexList.remove(selectedIndex);
        return;
    }

    public void addCourse(String courseID, int au, String school, String courseName) {
        Course newCourse = new Course(courseID, au, school, courseName);
        (courseInfo.getCourseList()).add(newCourse);
        return;
    }
    // private void editVacancy(Index){

    // }

    // public void editCourseAccess() {

    // }
}
