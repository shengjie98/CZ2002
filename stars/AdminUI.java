package stars;
import java.util.Scanner;

public class AdminUI extends SelectUI{
    public void displayMenu(){
        Scanner sc = new Scanner(System.in);
        int i;
        System.out.println("in admin display menu");
        do {
            System.out.println("1. Edit student access period");
            System.out.println("2. Add student");
            System.out.println("3. Add course");
            System.out.println("4. Update course");
            System.out.println("5. Check availablity slot for an index");
            System.out.println("6. Print student list by index number");
            System.out.println("7. Print student list by course");
            i = sc.nextInt();
            switch (i) {
                case 2:
                    addStudent();
                    break;
                default:
                    break;
            }

        } while (i > 0 && i < 8) ;
        return;
    }

    private void addStudent() {
        Scanner sc = new Scanner(System.in);
        String studentName, studentID, email, password, nationality, gender, degree, start, end;
        System.out.println("Add a Student");
        System.out.print("  a. Student Name: ");
        studentName = sc.next();
        System.out.print("  b. Student ID: ");
        studentID = sc.next();
        System.out.print("  c. Email: ");
        email = sc.next();
        System.out.print("  d. Password: ");
        password = sc.next();
        System.out.print("  e. Nationality: ");
        nationality = sc.next();
        System.out.print("  f. Gender: ");
        gender = sc.next();
        System.out.print("  g. Degree: ");
        degree = sc.next();
        System.out.print("  h. Access Perios Start (DD/MM/YYYY): ");
        start = sc.next();
        System.out.print("  i. Access Perios Start (DD/MM/YYYY): ");
        end = sc.next();
    }
}
