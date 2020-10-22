package stars;
import java.util.Scanner;

public class AdminUI {
    public void displayMenu(){
        Scanner sc = new Scanner(System.in);
        int i;
        System.out.println("in admin display menu");
        do {
            i = sc.nextInt();
        } while (i > 0) ;
        return;
    }
}
