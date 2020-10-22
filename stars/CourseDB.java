package stars;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles the logic dealing with the course data
 */
public class CourseDB implements Database {

    /**
     * Initialises a list
     */
    private ArrayList<Course> courseList = new ArrayList<Course>();

    /**
     * Retrieves the list of all the courses in the database
     * 
     * @return list of all courses in database
     */
    public ArrayList<Course> getCourseList() {
        return this.courseList;
    }

    /**
     * Add a new course into the database
     * 
     * @param course to be added into database
     */
    public void add(Course newCourse) {
        courseList.add(newCourse);
    }
    
	public void loadInformation() {
		// TODO Auto-generated method stub
        ArrayList<String[]> courses = new ArrayList<>();
        Scanner sc;
        String line;
        String delimiter = "[ ]+";

        try {
            // scan from courses.txt line by line into String line
            // split each line into token based on spaces and store into List courses
            sc = new Scanner(new File("courses.txt"));
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                courses.add(line.split(delimiter));
            }
            for (int i = 0; i < courses.size(); i++) {

                // initialise and set values for each array of strings in List courses
                int j = 0, k = 0, l = 0;
                String courseID = null;
                int au = -1;
                String index;
                String courseName = null;
                int[] capacity = null;
                String school = null;

                // school is the first string in the array
                school = courses.get(i)[0];

                // courseID is the second string in the array
                courseID = courses.get(i)[1];

                // index is the third string in the array
                index = courses.get(i)[2];

                // courseName is the fourth string in the array
                courseName = courses.get(i)[3];

                // au is the fifth in the array
                au = Integer.parseInt(courses.get(i)[4]);

                // capacity is the sixth string in the array and is set as the length of
                // lectureCapacity array
                capacity = new int[Integer.parseInt(courses.get(i)[5])];

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void saveInformation() {
        // TODO Auto-generated method stub

    }
}
