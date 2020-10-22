package stars;

import java.util.ArrayList;

public class StudentController {

    private Student aStudent;
    private CourseDB courseInfo;
    private StudentDB studentInfo;
    private StudentRegisteredCourses registeredIndex;

    public StudentController(String studentID) {
        courseInfo = new CourseDB();
        studentInfo = new StudentDB();
        aStudent = studentInfo.loadInfo(studentID);
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
    public boolean changeIndex(Index oldIndex, Index newIndex){
        return aStudent.changeIndex(oldIndex, newIndex);
    }
    public boolean swopIndex(String friendID, Index myIndex) {
        //using myIndex find myCourse, then find my friend's Index using myCourse
        Student friend = studentInfo.loadInfo(friendID); //find the student object for your friend
        Course myCourse = myIndex.getCourse(); //from the student object, find
        for (Index friendIndex : friend.getRegisteredIndex()){
            Course friendCourse = friendIndex.getCourse();
            if (friendCourse.getCourseID()==myCourse.getCourseID()) {//id
                Index foundIndex = friendIndex;
                if (friend.checkChangeIndex(myIndex, friendIndex) && aStudent.checkChangeIndex(friendIndex, myIndex)) {
                    aStudent.swopPlaces(myIndex, friendIndex);
                    friend.swopPlaces(friendIndex, myIndex);
                    return true;
                }
            }
        }
        return false;
    }
}
