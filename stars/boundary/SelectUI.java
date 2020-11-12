package stars.boundary;

import java.util.Scanner;
import java.util.ArrayList;
import stars.controller.*;
import stars.entity.*;
// import java.util.List;

public abstract class SelectUI {
    public abstract void displayMenu();

    /**
     * Lets the user choose from a list of Selectables
     * 
     * @return Selectable
     */
    public Selectable select(ArrayList<? extends Selectable> ls) {
        int i = 1;
        int choice;
        System.out.println("Select one from the following");
        Scanner sc = new Scanner(System.in);
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
