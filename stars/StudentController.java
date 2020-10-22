package stars;

public class StudentController {

    private String studentID;
    private Student aStudent;

    CourseDB courseInfo = new CourseDB;
    StudentDB studentInfo = new StudentDB;
    RegisteredCourses registeredInfo = new RegisteredCourses;

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
    // can add index and drop and change index be combined?
    public void dropIndex(){
        courseArray = aCourse.getIndexList();
        index = studentUI.select(courseArray);
        StudentRegisteredCourses.dropIndex(index);
    }
    public boolean checkVacanciesAvailable(){
        if (IndexClass.getVacancyLimit >= IndexClass.vacancy){
            return true;
        }
        return false;
    }
    public void changeIndex(){
        oldCourseArray = aCourse.getIndexList();
        oldIndex = studentUI.select(courseArray);
        newCourseArray = aCourse.
        StudentRegisteredCourses.changeIndex(oldIndex, newIndex);
    }
    public swapIndex(){
        courseArray = aCourse.getIndexList();
        yourIndex = studentUI.select(courseArray);

    }
        return

}
