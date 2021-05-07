import java.util.Scanner;

public class Menu {
    Scanner scanner = new Scanner(System.in);


    public Menu() {
    }

    public static void start() {
        AddressBook addressBook = new AddressBook();
        addressBook.populate();
        Menu menu = new Menu();
        menu.mainMenu(addressBook, menu);
    }

    public void mainMenu(AddressBook addressBook, Menu menu) {
        String verbo;
        System.out.println("Cosa vuoi fare?\n1)Visualizza tutte le voci in rubrica,\n2)Visualizza una voce in rubrica \n3)Aggiungi una voce in rubrica \n4)Modifica una voce in rubrica \n5)Elimina una voce in rubrica \n6)Ricerca per Nome\n0)Esci ");
        int num = scanner.nextInt();
        switch (num) {
            case 0:
                System.out.println("Grazie e Arrivederci!!!");
                break;
            case 1:
                menu.getAllMenu(addressBook, menu);
                break;
            case 2:
                verbo = "visualizzare";
                menu.getOneMenu(addressBook, menu, verbo);
                break;
            case 3:
                menu.addOne(addressBook, menu);
                break;
            case 4:
                verbo = "modificare";
                menu.getOneMenu(addressBook, menu, verbo);
                break;
            case 5:
                verbo = "eliminare";
                menu.getOneMenu(addressBook, menu, verbo);
                break;
            case 6:
                menu.searchByName(addressBook, menu);
                break;
            default:
                System.err.println("TASTO NON VALIDO!");
                menu.mainMenu(addressBook, menu);
        }
    }


    public void getAllMenu(AddressBook addressBook, Menu menu) {
        People[] agenda = addressBook.getRubricaAll();
        for (int i = 0; i < addressBook.getIndexMax(agenda); i++) {
            System.out.println("\n" + (i + 1));
            System.out.println("Nome:    " + agenda[i].getFirstName());
            if (agenda[i].getLastName() != null && agenda[i].getLastName() != "") {
                System.out.println("Cognome: " + agenda[i].getLastName());
            }
            System.out.println("Numero:  " + agenda[i].getNumber());
            if (agenda[i].getEmail() != null && agenda[i].getEmail() != "") {
                System.out.println("Email:   " + agenda[i].getEmail());
            }
        }
        menu.endOperation(addressBook, menu);
    }

    public void getOneMenu(AddressBook addressBook, Menu menu, String verbo) {
        System.out.println("Che posizione vuoi " + verbo + "?");
        People[] agenda = addressBook.getRubricaAll();
        int indexMax = addressBook.getIndexMax(agenda);
        System.out.println("L'ultimo numero inserito in rubrica è " + indexMax);
        int num = (scanner.nextInt() - 1);
        if (num < indexMax) {
            menu.getOne(addressBook, menu, num, verbo);
        } else {
            System.out.println("ERRORE!!! Questo contatto non esiste ancora in rubrica!!");
            menu.getOneMenu(addressBook, menu, verbo);
        }
    }

    public void getOne(AddressBook addressBook, Menu menu, int index, String verbo) {
        People persona = addressBook.getRubrica(index);
        System.out.println((index + 1) + " => Nome: " + persona.getFirstName());
        if (persona.getLastName() != null && persona.getLastName() != "") {
            System.out.println("   Cognome: " + persona.getLastName());
        }
        System.out.println("    Numero: " + persona.getNumber());
        if (persona.getEmail() != null && persona.getEmail() != "") {
            System.out.println("   Email:  " + persona.getEmail());
        }
        switch (verbo.charAt(0)) {
            case 'm' :
                menu.updateOne(addressBook, menu, (index));
                break;
            case 'e' :
                System.out.println("CONFERMI DI VOLER ELIMINARE?\nNUMERO QUALSIASI PER CONFERMARE\n0 PER ANNULLARE");
                int num = scanner.nextInt();
                if (num > 0) {
                    menu.deleteOne(addressBook, menu, (index));
                } else
                    menu.mainMenu(addressBook, menu);
                break;
            default:
                menu.endOperation(addressBook, menu);
        }
    }

    public void addOne(AddressBook addressBook, Menu menu) {
        People[] agenda = addressBook.getRubricaAll();
        int indexMax = addressBook.getIndexMax(agenda);
        scanner.nextLine();
        System.out.println("Inserimento posizione n: " + (indexMax + 1));
        System.out.println("Inserisci il Nome");
        String firstName = scanner.nextLine();
        if (firstName == null || firstName.trim().isEmpty()) {
            System.err.println("IL NOME E' OBBLIGATORIO!");
            menu.addOne(addressBook, menu);
        }
        System.out.println("Inserisci il cognome");
        String lastName = scanner.nextLine();
        System.out.println("Inserisci il Numero di telefono");
        String number = scanner.nextLine();
        if (!menu.checkNumber(number)) {
            System.err.println("FORMATO NON VALIDO\nIL NUMERO E' OBBLIGATORIO\nRicorda che il numero di telefono può avere solo 10 cifre");
            menu.addOne(addressBook, menu);
        } else {
            System.out.println("Inserisci l'indirizzo email");
            String email = scanner.nextLine();
            if(!menu.checkEmail(email)) {
                System.err.println("FORMATO NON VALIDO\nL'EMAIL E' OBBLIGATORIA");
                menu.addOne(addressBook, menu);
        } else {
            System.out.println("Ricapitolando...\n Nome: " + firstName + "\nCognome: " + lastName + "\nNumero: " + number + "\nEmail: " + email + "\n\n");
            System.out.println("CONFERMI? \n1)SI\n0)NO");
            int num = scanner.nextInt();
            switch (num) {
                case 1:
                    People persona = new People(firstName, lastName, number, email);
                    addressBook.insertPersona(persona, indexMax);
                    String verbo = "v";
                    menu.getOne(addressBook, menu, indexMax, verbo);
                    break;
                case 2:
                    menu.addOne(addressBook, menu);
                    break;
                default:
                    System.err.println("TASTO NON RICONOSCIUTO");
                }
            }
        }
    }
    public void updateOne(AddressBook addressBook, Menu menu, int i) {
        People persona = addressBook.getRubrica(i);
        System.out.println("Non scrivere nulla per lasciare il campo invariato");
        scanner.nextLine();
        System.out.println("Inserire il nuovo Nome:");
        String firstName = scanner.nextLine();
        if (firstName != null && !firstName.trim().isEmpty()) {
            persona.setFirstName(firstName);
        }
        System.out.println("Inserire il nuovo Cognome: ");
        String lastName = scanner.nextLine();
        if (lastName != null && !lastName.trim().isEmpty()) {
            persona.setLastName(lastName);
        }
        System.out.println("Inserire il nuovo numero di telefono");
        String number = scanner.nextLine();
        if (number != null && !number.trim().isEmpty()) {
            if (!menu.checkNumber(number)) {
                System.err.println("FORMATO NON VALIDO\nIL NUMERO NON VERRA' MODIFICATO");
            } else {
                persona.setNumber(number);
            }
        }
        System.out.println("Inserire la nuova email");
        String email = scanner.nextLine();
        if (email != null && !email.trim().isEmpty()) {
            if (!menu.checkEmail(email)) {
                System.err.println("FORMATO NON VALIDO\nL'EMAIL NON VERRA' MODIFICATA!!");
            } else {
                persona.setEmail(email);
            }
        }
        System.out.println("Contatto Aggiornato!");
        String verbo = "visualizzare";
        menu.getOne(addressBook, menu, i, verbo);
    }

    public void deleteOne(AddressBook addressBook, Menu menu, int i) {
        addressBook.deleteRubrica(i);
        menu.endOperation(addressBook, menu);
    }

    public void searchByName(AddressBook addressBook, Menu menu) {
        scanner.nextLine();
        System.out.println("Inserisci il Nome che vuoi cercare");
        String firstName = scanner.nextLine();
        People[] searchResult = addressBook.searchByName(firstName);
        for (int i = 0; i < searchResult.length; i++) {
            System.out.println("\n" + (i + 1));
            System.out.println("Nome:    " + searchResult[i].getFirstName());
            if (searchResult[i].getLastName() != null && searchResult[i].getLastName() != "") {
                System.out.println("Cognome: " + searchResult[i].getLastName());
            }
            System.out.println("Numero:  " + searchResult[i].getNumber());
            if (searchResult[i].getEmail() != null && searchResult[i].getEmail() != "") {
                System.out.println("Email:   " + searchResult[i].getEmail());
            }
        }
        menu.endOperation(addressBook, menu);
    }


    public void endOperation(AddressBook addressBook, Menu menu) {
        System.out.println("\n-------------------------\n1)Fai altre operazioni\n0)ESCI");
        int num = scanner.nextInt();
        switch (num) {
            case 0:
                System.out.println("Grazie e Arrivederci!!!");
                break;
            case 1:
                menu.mainMenu(addressBook, menu);
                break;
        }

    }
    public boolean checkNumber(String number) {
        boolean ok = false;
        if (number.matches("[0-9]+") && number.length() == 10) {
            ok = true;
        }
    return ok;
    }
    static boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }


}