package stars;

public class AdminController {
    CourseDB courseInfo = new CourseDB;
    StudentDB studentInfo = new StudentDB;

    /**
     * @return adds a student to an index of a course
     */
    public void addStudent(String studentName, String nationality, String gender, String studentID, String degree,
            String email) {
        // instantiate a new student object
        Student newStudent = new Student(studentName, nationality, gender, studentID, degree, email);
        // add this student to the StudentDB
        studentInfo.addStudent(newStudent);

    }

    public void editCourseInformation() {
        // rmb dont let them reduce vacancy past the current

        // length of the list of confirmed students
    }

    public void addCourse() {

    }

    public void editCourseAccess() {

    }

    public void printStudentList() {

    }
}
