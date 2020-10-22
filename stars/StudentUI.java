package stars;
import java.util.Scanner;

public class StudentUI extends SelectUI{
    public void displayMenu(){
        Scanner sc = new Scanner(System.in);
        int i;
        System.out.println("in student display menu");
        do {
            i = sc.nextInt();
        } while (i > 0) ;
        // sc.close();
        return;
    }
}
