package ex;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputEx {
static Scanner scanner = new Scanner(System.in);


    public static int nextInt() {
        int i = 0;
        try {
            i = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("HO DETTO UN NUMERO INTERO!");
            scanner.next();
            i = -1;
        }
        return i;
    }
}
