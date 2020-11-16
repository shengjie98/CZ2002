package stars.controller;

import java.util.ArrayList;
import stars.entity.*;
import stars.boundary.*;

public class StudentController {

    private Student myStudent;
    private DatabaseManager dbManager;
    private TimetableDisplayer displayer;

    public StudentController(String studentID) {
        dbManager = new DatabaseManager();
        dbManager.loadInformation();
        myStudent = dbManager.findStudent(studentID);
        displayer = new TimetableDisplayer(myStudent);
    }

    public ArrayList<Course> getCourseList() {
        return this.dbManager.getCoursesArray();
    }

    public boolean addIndex(Index myIndex) {
        int i;
        ArrayList<Index> allIndex = myStudent.getRegisteredIndex();
        if (allIndex.size() != 0) {
            for (i = 0; i < allIndex.size(); i++) {
                Index existingIndex = allIndex.get(i);
                if (existingIndex.getIndexNumber() == myIndex.getIndexNumber()) {
                    return false;
                }     
            }
        }
        return myStudent.addIndex(myIndex);
        // can add index and drop and change index be combined?
        
    }
    public boolean dropIndex (Index index){
        return myStudent.dropIndex(index);
    }
    
    public ArrayList<Index> getRegisteredIndex () {
        return myStudent.getRegisteredIndex();
    }

    public ArrayList<Index> getConfirmedIndex () {
        return myStudent.getConfirmedIndex();
    }

    public ArrayList<Index> getWaitlistedIndex () {
        return myStudent.getWaitlistedIndex();
    }

    
    
    public boolean changeIndex (Index oldIndex, Index newIndex){
        return myStudent.changeIndex(oldIndex, newIndex);
    }
    
    public boolean swopIndex (String friendID, Index myIndex){
        //using myIndex find myCourse, then find my friend's Index using myCourse
        Student friend = dbManager.findStudent(friendID); //find the student object for your friend
        Course myCourse = myIndex.getCourse(); //from the student object, find
        for (Index friendIndex : friend.getRegisteredIndex()) {
            Course friendCourse = friendIndex.getCourse();
            if (friendCourse.getCourseID() == myCourse.getCourseID()) {//id
                // Index foundIndex = friendIndex; 
                if (friend.checkChangeIndex(myIndex, friendIndex) && myStudent.checkChangeIndex(friendIndex, myIndex)) {
                    myStudent.swopPlaces(friendIndex, friend);
                    friend.swopPlaces(myIndex, this.myStudent);
                    return true;
                }
            }
        }
        return false;
    }

    public void save() {
        this.dbManager.saveInformation();
    }

    public void displayTimetable() {
        this.displayer.displayTimetable();
    }

}
