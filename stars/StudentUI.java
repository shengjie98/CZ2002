package stars;
import java.util.Scanner;

public class StudentUI extends SelectUI{
    public void displayMenu(){
        Scanner sc = new Scanner(System.in);
        int i;
        System.out.println("in student display menu");
        do {
            i = sc.nextInt();
            // switch case 
            // for add student need to take in name, nationality, gender, matric number, degree, email, password, access
        } while (i > 0) ;
        
        return;
    }
}
