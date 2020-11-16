package stars.boundary;

import java.util.Scanner;
import java.util.ArrayList;
import stars.entity.*;

/**
 * implementation of select method to be used by UI classes
 */
public abstract class SelectUI {

    /**
     * Lets the user choose from a list of Selectables
     * @return Selectable
     */
    public Selectable select(ArrayList<? extends Selectable> ls) {
        int i = 1;
        int choice;
        System.out.println("Select one from the following");
        Scanner sc = new Scanner(System.in);
        // if the size of the list is zero, return null
        if (ls.size() == 0) {
            return null;
        }
        for (i = 1; i <= ls.size(); i++) {
            System.out.printf("  %d: %s\n", i, ls.get(i - 1).print());
        }
        System.out.print("Option: ");
        do {
            choice = sc.nextInt();
        } while (choice < 0 || choice > ls.size());
        return ls.get(choice - 1);
    }
}
