package stars;

import stars.Index;

/**
 * 
 * @return class for stars.Course objects
 */

public class Course {
    private String courseID;
    private Index[] indexList;
    private int au;
    private String school;
    private String courseName;

    public Course(String courseID, Index[] indexList, int au, String school, String courseName) {
        this.courseID = courseID;
        this.indexList = indexList;
        this.au = au;
        this.school = school;
        this.courseName = courseName;
    }

    /**
     * 
     * @return an array of stars.Index objects for the course
     */
    public Index[] getIndex() {
        return indexList;
    }

    /**
     * 
     * @return the au
     */
    public int getAU() {
        return au;
    }

    /**
     * 
     * @param CourseID
     * @return set the courseID
     */
    public void setCourseID(String CourseID) {
        this.courseID = CourseID;
    }

    /**
     * 
     * @param AU
     * @return set the AU
     */
    public void setAU(int AU) {
        this.au = AU;
    }

    /**
     * 
     * @param School
     * @return set the school that the course belongs to
     */
    public void setSchool(String School) {
        this.school = School;
    }

    /**
     * 
     * @param CourseName
     * @return set the name of the course
     */
    public void setCourseName(String CourseName) {
        this.courseName = CourseName;
    }

}