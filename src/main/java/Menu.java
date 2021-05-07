import java.util.ArrayList;
import java.util.Scanner;

/**
 * classe Menu gestisce le varie operazioni
 * e la stampa a video dei dati
 */
public class Menu {
    Scanner scanner = new Scanner(System.in);


    public Menu() {
    }

    /**
     * lanciata nel main istanzia l'oggetto agenda e la popola con 10 inserimenti con dati casuali
     */
    public void start() {
        User owner = User.createOwner();
        Menu menu = new Menu();
        AddressBook addressBook = new AddressBook(owner);
        System.out.println("RUBRICA PROTETTA DA PASSWORD!!!");
        String password = scanner.nextLine();
        if (!password.equals(addressBook.getOwner().getPassword())) {
            System.err.println("PASSWORD ERRATA!!!");
        } else {
            System.out.println("Quanti Contatti casuali vuoi generare?\nIl limite massimo è 50");
            int num = scanner.nextInt();
            if (num <= 50) {
                addressBook.populate(num);
                menu.mainMenu(addressBook, menu);
            } else {
                System.err.println("NUMERO TROPPO ALTO!");
                menu.start();
            }
        }
    }
    /**
     *
     * @param addressBook oggetto istanziato e popolato in Menu.start() contenente tutti i dati della rubrica
     * @param menu oggetto istanziato in start
     */

    public void mainMenu(AddressBook addressBook, Menu menu) {
        System.out.println("Ciao " + addressBook.getOwner().getFirstName() + " " + addressBook.getOwner().getLastName());
        System.out.println("Hai " + addressBook.getAll().size() + " contatti in rubrica");
        String verbo;
        System.out.println("Cosa vuoi fare?\n1)Visualizza tutte le voci in rubrica,\n2)Visualizza una voce in rubrica \n3)Aggiungi una voce in rubrica \n4)Modifica una voce in rubrica \n5)Elimina una voce in rubrica \n6)Ricerca per Nome\n7)Modifica informazioni utente\n0)Esci ");
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
            case 7:
                menu.updateOwner(addressBook, menu);
                break;
            default:
                System.err.println("TASTO NON VALIDO!");
                menu.mainMenu(addressBook, menu);
        }
    }

    /**
     *
     * @param addressBook oggetto istanziato e popolato in Menu.start() contenente tutti i dati della rubrica
     * @param menu oggetto istanziato in start
     */
    public void getAllMenu(AddressBook addressBook, Menu menu) {
        ArrayList<People> rubrica = addressBook.getAll();
        for (int i = 0; i < rubrica.size(); i++) {
            System.out.println("\n" + (i + 1));
            System.out.println("Nome:    " + rubrica.get(i).getFirstName());
            if (rubrica.get(i).getLastName() != null && rubrica.get(i).getLastName() != "") {
                System.out.println("Cognome: " + rubrica.get(i).getLastName());
            }
            System.out.println("Numero:  " + rubrica.get(i).getNumber());
            if (rubrica.get(i).getEmail() != null && rubrica.get(i).getEmail() != "") {
                System.out.println("Email:   " + rubrica.get(i).getEmail());
            }
        }
        menu.endOperation(addressBook, menu);
    }

    /**
     * getOneMenu Permette di selezionare un contatto nella rubricsa per visualizzarlo, modificarlo o eliminarlo
     * @param addressBook oggetto istanziato e popolato in Menu.start() contenente tutti i dati della rubrica
     * @param menu oggetto istanziato in start
     * @param verbo stringa contenente il verbo da stampare a video (visualizzare, modificare e eliminare) in getOneMenu;
     *
     */
    public void getOneMenu(AddressBook addressBook, Menu menu, String verbo) {
        ArrayList<People> rubrica = addressBook.getAll();

        System.out.println("Che posizione vuoi " + verbo + "?");
        int indexMax = rubrica.size();
        System.out.println("L'ultimo numero inserito in rubrica è " + indexMax);
        int num = (scanner.nextInt()-1);
        if (num < indexMax) {
            menu.getOne(addressBook, menu, num, verbo);
        } else {
            System.out.println("ERRORE!!! Questo contatto non esiste ancora in rubrica!!");
            menu.getOneMenu(addressBook, menu, verbo);
        }
    }

    /**
     * Stampa a video il contatto e, a seconda dell'azione intrapresa, esso verrà poi modificato o eliminato.
     * @param addressBook oggetto istanziato e popolato in Menu.start() contenente tutti i dati della rubrica
     * @param menu oggetto istanziato in start
     * @param index numero del contatto in rubrica addressBook.getRubrica(index)
     * @param verbo verbo.charAt(0) usato come condizione dello switch in getOne;
     */
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

    /**
     * Inserisce un nuovo contatto in rubrica subito dopo l'ultima posizione popolata
     * effettua i vari controlli sugli input
     * infine reindirizza su getOne per visualizzare il contatto inserito
     * @param addressBook oggetto istanziato e popolato in Menu.start() contenente tutti i dati della rubrica
     * @param menu oggetto istanziato in start
     */
    public void addOne(AddressBook addressBook, Menu menu) {
        ArrayList<People> rubrica = addressBook.getAll();
        int indexMax = rubrica.size();
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
                    addressBook.insertPersona(persona                     );
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
    /**
     * Modifica il contatto
     * effettua i vari controlli sugli input
     * infine reindirizza su getOne per visualizzare il contatto inserito
     * @param addressBook oggetto istanziato e popolato in Menu.start() contenente tutti i dati della rubrica
     * @param menu oggetto istanziato in start
     * @param i indice dell'oggetto da modificare
     */
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
    /**
     * Elimina il contatto selezionato
     * @param addressBook oggetto istanziato e popolato in Menu.start() contenente tutti i dati della rubrica
     * @param menu oggetto istanziato in start
     * @param i indice dell'oggetto da eliminare
     */
    public void deleteOne(AddressBook addressBook, Menu menu, int i) {
        addressBook.deleteRubrica(i);
        menu.endOperation(addressBook, menu);
    }

    /**
     * Effettua la ricerca tramite nome
     * @param addressBook oggetto istanziato e popolato in Menu.start() contenente tutti i dati della rubrica
     * @param menu oggetto istanziato in start
     */
    public void searchByName(AddressBook addressBook, Menu menu) {
        scanner.nextLine();
        System.out.println("Inserisci il Nome che vuoi cercare");
        String firstName = scanner.nextLine();
        ArrayList<People> searchResult = addressBook.searchByName(firstName, addressBook);
        if(searchResult.size() > 0) {
            for (int i = 0; i < searchResult.size(); i++) {
                System.out.println("\n" + (i + 1));
                System.out.println("Nome:    " + searchResult.get(i).getFirstName());
                if (searchResult.get(i).getLastName() != null && searchResult.get(i).getLastName() != "") {
                    System.out.println("Cognome: " + searchResult.get(i).getLastName());
                }
                System.out.println("Numero:  " + searchResult.get(i).getNumber());
                if (searchResult.get(i).getEmail() != null && searchResult.get(i).getEmail() != "") {
                    System.out.println("Email:   " + searchResult.get(i).getEmail());
                }
            }
        } else {
            System.out.println("La ricerca non ha prodotto risultati");
        }
        menu.endOperation(addressBook, menu);
    }

    /**
     * Da la possibilità di effettuare nuove operazioni sulla rubrica o uscire
     * @param addressBook oggetto istanziato e popolato in Menu.start() contenente tutti i dati della rubrica
     * @param menu oggetto istanziato in start
     */
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

    /**
     * Controlla la formattazione della String numero inserita dall'utente
     * @param number Stringa inserita in input dall'utente
     * @return boolean true = Il formato è corretto
     *                 false= Il formato è errato
     */
    public boolean checkNumber(String number) {
        boolean ok = false;
        if (number.matches("[0-9]+") && number.length() == 10) {
            ok = true;
        }
    return ok;
    }
    /**
     * Controlla la formattazione della String email inserita dall'utente
     * @param email Stringa inserita in input dall'utente
     * @return boolean true = Il formato è corretto
     *                 false= Il formato è errato
     */
    static boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
    public void updateOwner(AddressBook addressBook, Menu menu) {
        System.out.println("VISUALIZZA E MODIFICA INFORMAZIONI UTENTE");
        addressBook.getOwner().printAll();
        System.out.println("Vuoi modificare le informazioni?\nNUMERO QUALSIASI)SI\n0)NO");
        int num = scanner.nextInt();
        if (num > 0) {
            User owner = addressBook.getOwner();
            System.out.println("Non scrivere nulla per lasciare il campo invariato");
            scanner.nextLine();
            System.out.println("Inserire il nuovo Nome:");
            String firstName = scanner.nextLine();
            if (firstName != null && !firstName.trim().isEmpty()) {
                owner.setFirstName(firstName);
            }
            System.out.println("Inserire il nuovo Cognome: ");
            String lastName = scanner.nextLine();
            if (lastName != null && !lastName.trim().isEmpty()) {
                owner.setLastName(lastName);
            }
            System.out.println("Inserire il nuovo numero di telefono:");
            String number = scanner.nextLine();
            if (number != null && !number.trim().isEmpty()) {
                if (!menu.checkNumber(number)) {
                    System.err.println("FORMATO NON VALIDO\nIL NUMERO NON VERRA' MODIFICATO");
                } else {
                    owner.setNumber(number);
                }
            }
            System.out.println("Inserire la nuova email:");
            String email = scanner.nextLine();
            if (email != null && !email.trim().isEmpty()) {
                if (!menu.checkEmail(email)) {
                    System.err.println("FORMATO NON VALIDO\nL'EMAIL NON VERRA' MODIFICATA!!");
                } else {
                    owner.setEmail(email);
                }
            }
            System.out.println("Inserire la nuova password:");
            String password = scanner.nextLine();
            if (password != null && !password.trim().isEmpty()) {

                    owner.setEmail(email);
            }

            System.out.println("Utente Aggiornato!");

        }
        menu.endOperation(addressBook, menu);
        }

    }
