package stars.controller;


import java.util.ArrayList;
import stars.entity.*;

public interface DatabaseManagerInterface {
    public ArrayList<Student> getStudentArray();
    public ArrayList<Course> getCoursesArray();
    public boolean addStudent(Student student);
    public boolean addCourse(Course course);
    public Student findStudent(String studentID);
    public void saveInformation();
}
