package stars.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Course handles the data that pertains to a Course
 */
public class Course implements Selectable, Serializable {

    private String courseID;
    private String courseName;
    private ArrayList<Index> indexList = new ArrayList<Index>();
    private int au;
    private String school;

    /**
     * The constructor for the Course class
     * 
     * @param courseID   The ID of the Course
     * @param au         The number of AUs for the Course
     * @param school     The School that the Course belongs to
     * @param courseName The name of the Course
     */
    public Course(String courseID, int au, String school, String courseName) {
        this.courseID = courseID;
        this.au = au;
        this.school = school;
        this.courseName = courseName;
    }

    /**
     * adds the Index to list of Indexes that the Courses has
     * 
     * @param newIndex The new Index to be added to the Course
     * @return boolean that indicates whether the Index has been added to the Course
     */
    public boolean addIndex(Index newIndex) {
        for (Index index : indexList) {
            if (index.getIndexNumber() == newIndex.getIndexNumber()) {
                return false;
            }
        }
        indexList.add(newIndex);
        return true;
    }

    /**
     * Gets the Course ID of the Course
     * 
     * @return String of the Course ID
     */
    public String getCourseID() {
        return courseID;
    }

    /**
     * Sets the Course ID to be the new Course ID
     * 
     * @param courseID The new Course ID to be added
     */
    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    /**
     * Gets ArrayList of Indexes of the Course
     * 
     * @return The list of Indexes of the Course
     */
    public ArrayList<Index> getIndexList() {
        return indexList;
    }

    /**
     * Sets the list of Indexes to be an Array List of Indexes
     * 
     * @param indexList The Array List of Indexes to be set in the indexList
     */
    public void setIndexList(ArrayList<Index> indexList) {
        this.indexList = indexList;
    }

    /**
     * Gets the nummber of AUs of this Course
     * 
     * @return The number of AUs of this Course
     */
    public int getAu() {
        return au;
    }

    /**
     * Sets the number of AUs of this Course
     * 
     * @param au The number of AUs of the Course
     */
    public void setAu(int au) {
        this.au = au;
    }

    /**
     * Gets the name of the School that the Course belongs to
     * 
     * @return The name of the School
     */
    public String getSchool() {
        return school;
    }

    /**
     * Sets the name of the School that the Course belongs to
     * 
     * @param school The name that the School should be set to
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * Get the Course name of the Course
     * 
     * @return The Course name of the Course
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * Set the Course name of the Course
     * 
     * @param courseName The Course name to be set to
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * Prints out the Information about the Course
     * 
     * @return The information about the Course
     */
    public String print() {
        // print out all the information of the Course
        String stringToReturn = this.getCourseID() + ' ' + this.getSchool() + ' ' + this.getCourseName() + ' ';
        return stringToReturn;
    }

}