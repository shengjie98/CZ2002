package stars;

public class StudentController {

    private String studentID;
    private Student aStudent;

    CourseDB courseInfo = new CourseDB();
    StudentDB studentInfo = new StudentDB();
    Course allCourses = new Course();
    StudentRegisteredCourses registeredInfo = new StudentRegisteredCourses();
    StudentUI aStudentUI = new StudentUI();
    Index aIndex = new Index();

    public StudentController(String studentID) {
        this.studentID = studentID;
        aStudent = studentInfo.loadInfo(studentID);
    }

    public void addIndex(){
        Index allIndexArray[] = allCourses.getIndexList(); //might change the first word to Index
        String index = aStudentUI.select(allIndexArray); //check array
        registeredInfo.addIndex(index);
    }
    // can add index and drop and change index be combined?
    public void dropIndex(){
        RegisteredCourses registeredCourseArray[] = registeredInfo.getIndexList();
        String index = aStudentUI.select(registeredCourseArray);
        registeredInfo.dropIndex(index);
    }
    public boolean checkVacanciesAvailable(){
        if (aIndex.getVacancy() >= aIndex.getConfirmedList().length){
            return true;
        }
        return false;
    }
    public void changeIndex(){
        Index registeredIndexArray[] = registeredInfo.getIndexList();
        String oldIndex = aStudentUI.select(registeredIndexArray);
        Index allIndexArray[] = allCourses.getIndexList();
        String newIndex = aStudentUI.select(allIndexArray);
        registeredInfo.changeIndex(oldIndex, newIndex);
    }
    public void swapIndex() {
        Index registeredIndexArray[] = registeredInfo.getIndexList();
        String myIndex = aStudentUI.select(registeredIndexArray);
        registeredInfo.changeIndex(myIndex);
    }

}
