package stars;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentUI extends SelectUI{
    StudentController studentController;
    
    public StudentUI (String studentID) {
        this.studentController = new StudentController(studentID);
    }
    
    
    public void displayMenu(){
        
        Scanner sc = new Scanner(System.in);
        int i;
        System.out.println("in admin display menu");
        do {
            System.out.println("1. Add Course Index");
            System.out.println("2. Add student");
            System.out.println("3. Add course");
            System.out.println("4. Update course");
            System.out.println("5. Check availablity slot for an index");
            System.out.println("6. Print student list by index number");
            System.out.println("7. Print student list by course");
            System.out.print("Option: ");
            i = sc.nextInt();
            switch (i) {
                case 1:
                    addIndex();
                    break;
                default:
                    break;
            }

        } while (i > 0 && i < 8) ;
        return;
    }

    public void addIndex() {
        ArrayList<Course> courseList;
        Course selectedCourse;
        ArrayList<Index> indexList;
        Index selectedIndex;
        
        courseList = studentController.getCourseList();
        selectedCourse = (Course)select(indexList);
    }
}
