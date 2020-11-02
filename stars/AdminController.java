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
    public void addStudent(String studentName, String nationality, String gender, String studentID, String degree, String email) {
        // instantiate a new student object
        Student newStudent = new Student(studentName, nationality, gender, studentID, degree, email);
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
    public boolean dropIndex(Course selectedCourse, Index selectedIndex) {
        ArrayList<Course> listOfCourses = courseInfo.getCourseList();
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
        courseInfo.add(newCourse);
        return;
    }
    // private void editVacancy(Index){

    // }

    // public void editCourseAccess() {

    // }
}
