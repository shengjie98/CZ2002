package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;
import stars.boundary.*;
import stars.controller.*;

/**
 * 
 * @return class for stars.Course objects
 */

public class Course implements Selectable, Serializable {

    private String courseID;
    private String courseName;
    private ArrayList<Index> indexList = new ArrayList<Index>();
    private int au;
    private String school;

    public Course(String courseID, int au, String school, String courseName) {
        this.courseID = courseID;
        this.au = au;
        this.school = school;
        this.courseName = courseName;
    }

    public boolean addIndex(Index newIndex) {
        for (Index index : indexList) {
            if (index.getIndexNumber() == newIndex.getIndexNumber()) {
                return false;
            }
        }
        indexList.add(newIndex);
        return true;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public ArrayList<Index> getIndexList() {
        return indexList;
    }

    public void setIndexList(ArrayList<Index> indexList) {
        this.indexList = indexList;
    }

    public int getAu() {
        return au;
    }

    public void setAu(int au) {
        this.au = au;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String print() {
        // print out all the information of the Course
        String stringToReturn = this.getCourseID() + ' ' + this.getSchool() + ' ' + this.getCourseName() + ' ';
        return stringToReturn;
    }

}