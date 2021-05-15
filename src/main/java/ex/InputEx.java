package ex;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputEx {

    /**
     * Gestione dell'eccezione InputMismatchException
     * @return int nextInt (in caso di eccezione nextInt sarà uguale a -1)
     */
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

    /**
     * Gestisce l'input next line impedendo loop, o altri problemi
     * @return String nextLn
     */
    public static String nextLn() {
        Scanner scanner = new Scanner(System.in);
            String nextLn = scanner.nextLine();
            if (nextLn.trim().length() <= 0 || nextLn.isEmpty()) {
                System.out.println("Errore");
            }
            return nextLn;
    }
    /**
     * Controlla la formattazione della String numero inserita dall'utente
     *
     * @param number Stringa inserita in input dall'utente
     * @return boolean true = Il formato è corretto
     * false= Il formato è errato
     */

    public static boolean checkNumber(String number) {
        boolean ok = false;
        if (number.matches("[0-9]+") && number.length() == 10) {
            ok = true;
        }
        return ok;
    }

    /**
     * Controlla la formattazione della String email inserita dall'utente
     *
     * @param email Stringa inserita in input dall'utente
     * @return boolean true = Il formato è corretto
     * false= Il formato è errato
     */
    public static boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}

