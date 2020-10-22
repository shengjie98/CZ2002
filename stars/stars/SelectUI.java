package stars;
import java.util.Scanner;

public abstract class SelectUI {
    public abstract void displayMenu();

    /**
     * Lets the user choose from a list of Selectables
     * @return Selectable 
     */
    public Selectable select(Selectable[] ls) {
        int i = 1;
        int choice;
        System.out.println("Select one from the following");
        Scanner sc = new Scanner(System.in);
        for (i = 1; i <= ls.length; i++) {
            System.out.printf("%d: %s\n", i , ls[i-1].print());
        }
        System.out.print("Option: ");
        do {
            choice = sc.nextInt();
        } while (choice < 0 || choice > ls.length);
        return ls[choice-1];
    }
}
