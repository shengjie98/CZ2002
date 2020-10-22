package stars;

import stars.Index;

/**
 * 
 * @return class for stars.Course objects
 */

public class Course implements Selectable {
    private String courseID;
    private String courseName;
    private Index[] indexList;
    private int au;
    private String school;

    public Course(String courseID, Index[] indexList, int au, String school, String courseName) {
        this.courseID = courseID;
        this.indexList = indexList;
        this.au = au;
        this.school = school;
        this.courseName = courseName;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public Index[] getIndexList() {
        return indexList;
    }

    public void setIndexList(Index[] indexList) {
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
        String stringToReturn = this.getCourseID() + '\n' + this.getSchool() + '\n' + this.getCourseID() + '\n'
                + this.getAu() + '\n';
        return stringToReturn;
    }

}