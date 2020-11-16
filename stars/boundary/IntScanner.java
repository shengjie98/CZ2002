package stars.boundary;

import java.io.InputStream;
import java.util.Scanner;

public class IntScanner {

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
