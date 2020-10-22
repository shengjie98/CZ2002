package stars;
import java.util.Scanner;

public abstract class SelectUI {
    public abstract void displayMenu();

    public Selectable select(Selectable[] ls) {
        int i = 1;
        int choice;
        System.out.println("Select one from the following");
        Scanner sc = new Scanner(System.in);
        for (i = 1; i <= ls.length; i++) {
            System.out.printf("%i: ", i );
            ls[i-1].print();
        }
        System.out.print("Option: ");
        do {
            choice = sc.nextInt();
        } while (choice < 0 || choice > ls.length);
        return ls[choice-1];
    }
}
