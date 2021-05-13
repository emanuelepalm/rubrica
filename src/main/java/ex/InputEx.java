package ex;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputEx {


    public static int nextInt() {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        try {
            i = scanner.nextInt();
        } catch (InputMismatchException ex) {
            System.err.println("HO DETTO UN NUMERO INTERO!");
            i = -1;
        }
        return i;
    }
}
