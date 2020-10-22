public class StudentController {

    private String studentID;
    private Student aStudent;

    CourseDB courseInfo = new CourseDB;
    StudentDB studentInfo = new StudentDB;

    public StudentController(String studentID) {
        this.studentID = studentID;
        aStudent = studentInfo.loadInfo(studentID);
    }

    Course aCourse = new Course();
    public void addIndex(){
        courseArray = aCourse.getIndexList();
        index = studentUI.select(courseArray);
        StudentRegisteredCourses.addIndex(index);
    }
    // can add index and drop index be combined?
    public void dropIndex(){
        courseArray = aCourse.getIndexList();
        index = studentUI.select(courseArray);
        StudentRegisteredCourses.dropIndex(index);
    }
    public void checkVacanciesAvailable(){

    }
    public changeIndex(){
        return;
    }
    public swapIndex()
        return

}
