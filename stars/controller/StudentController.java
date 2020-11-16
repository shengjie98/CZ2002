package stars.controller;

import java.util.ArrayList;
import stars.entity.*;
import stars.exceptions.*;
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

    public boolean addIndex(Index myIndex) throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        return myStudent.addIndex(myIndex);
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

    
    
    public boolean changeIndex (Index oldIndex, Index newIndex) throws ExceedAUException, TimetableClashException, AlreadyRegisteredException {
        return myStudent.changeIndex(oldIndex, newIndex);
    }
    
    public boolean swopIndex (String friendID, Index myIndex) throws TimetableClashException, AlreadyRegisteredException {
        //using myIndex find myCourse, then find my friend's Index using myCourse
        Student friend = dbManager.findStudent(friendID); //find the student object for your friend
        Course myCourse = myIndex.getCourse(); //from the student object, find
        for (Index friendIndex : friend.getRegisteredIndex()) {
            Course friendCourse = friendIndex.getCourse();
            if (friendCourse.getCourseID().equals(myCourse.getCourseID())) {//id
                // check that index is not the same
                if (friendIndex.getIndexNumber() == myIndex.getIndexNumber()) {
                    throw new AlreadyRegisteredException();
                }
                
                if (friend.checkChangeIndex(myIndex, friendIndex) && myStudent.checkChangeIndex(friendIndex, myIndex)) {
                    friend.swopPlaces(myIndex, this.myStudent);
                    return myStudent.swopPlaces(friendIndex, friend);
                } 
            }
        }
        throw new TimetableClashException();
    }

    public void save() {
        this.dbManager.saveInformation();
    }

    public void displayTimetable() {
        this.displayer.displayTimetable();
    }

}
