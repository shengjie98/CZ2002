package stars.boundary;

import java.util.Scanner;

/**
 * Int Scanner with error handling
 */
public class IntScanner {
    /**
     * Int Scanner with error handing
     * 
     * @return int from sys.in
     */
    public static int nextInt() {
        int i;
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                i = sc.nextInt();
                return i;
            } catch (Exception e) {
                System.out.print("Invalid format! Enter a number: ");
            }
        }
    }
}
