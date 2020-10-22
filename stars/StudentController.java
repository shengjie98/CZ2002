package stars;

public class StudentController {

//    private String studentID;
    private Student aStudent;
    private CourseDB courseInfo;
    private StudentDB studentInfo;
    private StudentRegisteredCourses registeredIndex;

    public StudentController(String studentID) {
        courseInfo = new CourseDB();
        studentInfo = new StudentDB();
        aStudent = studentInfo.loadInfo(studentID);
//        this.studentID = studentID;
//        Course allCourses = new Course(a,s,d,f,g)
//        Index aIndex = new Index(a,s,d,f,g,h);
//
//        aStudent = studentInfo.loadInfo(studentID);
    }

    public ArrayList<Course> getCourseList() {
        return this.courseInfo.getCourseList();
    }

    public boolean addIndex(Index index){
        return aStudent.addIndex(index);
    }
    // can add index and drop and change index be combined?
    public boolean dropIndex(Index index){
        return aStudent.dropIndex(index);
    }
    public ArrayList<Index> getRegisteredIndex(){
        return aStudent.getRegisteredIndex();
    }
    public boolean changeIndex(){
        return aStudent.changeIndex(oldIndex, newIndex);
    }
    public boolean swopIndex(String friendID, Index index) {
        studentInfo.loadInfo()
    }

}
