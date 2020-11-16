package stars.boundary;

import java.io.InputStream;
import java.util.Scanner;

public class IntScanner{

    public static int nextInt(){
        int i;
        Scanner sc = new Scanner(System.in);
        while (true) {
            try {
                i = sc.nextInt();
                return i;
            } catch (Exception e) {
                System.out.println("Invalid format!");
            }
        }
    }
}
