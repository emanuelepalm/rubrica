import people.Contact;
import people.User;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressBook implements Menu {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Contact> rubrica = new ArrayList<Contact>(10);
    private User owner;
    public AddressBook(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }

    public void populate(int i) {
        for (int j = 0; j < i; j++) {
            Contact persona = Contact.populate();
            this.rubrica.add(j, persona);
        }
    }

    public ArrayList<Contact> getAll() {
        return this.rubrica;
    }

    public Contact getRubrica(int i) {
        return this.rubrica.get(i);
    }

    public void deleteRubrica(int i) {
        this.rubrica.remove(i);
    }

    public void insertPersona(Contact persona) {
        this.rubrica.add(persona);
    }

    public ArrayList<Contact> searchByName(String firstName) {
        ArrayList<Contact> searchResult = new ArrayList<Contact>(10);
        for (int i = 0; i < this.rubrica.size(); i++) {
            if (this.rubrica.get(i).equals(firstName)) {
                searchResult.add(i, this.rubrica.get(i));
            }
        }
        return searchResult;
    }

    @Override
    public void start() {
        
        System.out.println("RUBRICA PROTETTA DA PASSWORD!!!");
        String password = scanner.nextLine();
        if (!password.equals(this.getOwner().getPassword())) {
            System.err.println("PASSWORD ERRATA!!!");
        } else {
            System.out.println("Quanti Contatti casuali vuoi generare?\nIl limite massimo è 50");
            int num = scanner.nextInt();
            if (num <= 50) {
                this.populate(num);
                mainMenu();
            } else {
                System.err.println("NUMERO TROPPO ALTO!");
                start();
            }
        }
    }

    @Override
    public void mainMenu() {
        System.out.println("Ciao " + this.getOwner().getFirstName() + " " + this.getOwner().getLastName());
        System.out.println("Hai " + this.getAll().size() + " contatti in rubrica");
        String verbo;
        System.out.println("Cosa vuoi fare?\n1)Visualizza tutte le voci in rubrica,\n2)Visualizza una voce in rubrica \n3)Aggiungi una voce in rubrica \n4)Modifica una voce in rubrica \n5)Elimina una voce in rubrica \n6)Ricerca per Nome\n7)Modifica informazioni utente\n0)Esci ");
        int num = scanner.nextInt();
        switch (num) {
            case 0:
                System.out.println("Grazie e Arrivederci!!!");
                break;
            case 1:
                getAllMenu();
                break;
            case 2:
                verbo = "visualizzare";
                getOneMenu(verbo);
                break;
            case 3:
                addOne();
                break;
            case 4:
                verbo = "modificare";
                getOneMenu(verbo);
                break;
            case 5:
                verbo = "eliminare";
                getOneMenu(verbo);
                break;
            case 6:
                searchByName();
                break;
            case 7:
                updateOwner();
                break;
            default:
                System.err.println("TASTO NON VALIDO!");
                mainMenu();
        }
    }

    @Override
    public void getAllMenu() {
        ArrayList<Contact> rubrica = this.getAll();
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
        endOperation();
    }

    @Override
    public void getOneMenu(String verbo) {
        ArrayList<Contact> rubrica = this.getAll();

        System.out.println("Che posizione vuoi " + verbo + "?");
        int indexMax = rubrica.size();
        System.out.println("L'ultimo numero inserito in rubrica è " + indexMax);
        int num = (scanner.nextInt() - 1);
        if (num < indexMax) {
            getOne(num, verbo);
        } else {
            System.out.println("ERRORE!!! Questo contatto non esiste ancora in rubrica!!");
            getOneMenu(verbo);
        }
    }

    @Override
    public void getOne(int index, String verbo) {
        Contact persona = this.getRubrica(index);
        System.out.println((index + 1) + " => Nome: " + persona.getFirstName());
        if (persona.getLastName() != null && persona.getLastName() != "") {
            System.out.println("   Cognome: " + persona.getLastName());
        }
        System.out.println("    Numero: " + persona.getNumber());
        if (persona.getEmail() != null && persona.getEmail() != "") {
            System.out.println("   Email:  " + persona.getEmail());
        }
        switch (verbo.charAt(0)) {
            case 'm':
                updateOne(index);
                break;
            case 'e':
                System.out.println("CONFERMI DI VOLER ELIMINARE?\nNUMERO QUALSIASI PER CONFERMARE\n0 PER ANNULLARE");
                int num = scanner.nextInt();
                if (num > 0) {
                    deleteOne(index);
                } else
                    mainMenu();
                break;
            default:
                endOperation();
        }
    }

    @Override
    public void addOne() {
        ArrayList<Contact> rubrica = this.getAll();
        int indexMax = rubrica.size();
        scanner.nextLine();
        System.out.println("Inserimento posizione n: " + (indexMax + 1));
        System.out.println("Inserisci il Nome");
        String firstName = scanner.nextLine();
        if (firstName == null || firstName.trim().isEmpty()) {
            System.err.println("IL NOME E' OBBLIGATORIO!");
            addOne();
        }
        System.out.println("Inserisci il cognome");
        String lastName = scanner.nextLine();
        System.out.println("Inserisci il Numero di telefono");
        String number = scanner.nextLine();
        if (!checkNumber(number)) {
            System.err.println("FORMATO NON VALIDO\nIL NUMERO E' OBBLIGATORIO\nRicorda che il numero di telefono può avere solo 10 cifre");
            addOne();
        } else {
            System.out.println("Inserisci l'indirizzo email");
            String email = scanner.nextLine();
            if (!checkEmail(email)) {
                System.err.println("FORMATO NON VALIDO\nL'EMAIL E' OBBLIGATORIA");
                addOne();
            } else {
                System.out.println("Ricapitolando...\n Nome: " + firstName + "\nCognome: " + lastName + "\nNumero: " + number + "\nEmail: " + email + "\n\n");
                System.out.println("CONFERMI? \n1)SI\n0)NO");
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        Contact persona = new Contact(firstName, lastName, number, email);
                        this.insertPersona(persona);
                        String verbo = "v";
                        getOne(indexMax, verbo);
                        break;
                    case 2:
                        addOne();
                        break;
                    default:
                        System.err.println("TASTO NON RICONOSCIUTO");
                }
            }
        }
    }

    @Override
    public void updateOne(int i) {
        Contact persona = this.getRubrica(i);
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
            if (!checkNumber(number)) {
                System.err.println("FORMATO NON VALIDO\nIL NUMERO NON VERRA' MODIFICATO");
            } else {
                persona.setNumber(number);
            }
        }
        System.out.println("Inserire la nuova email");
        String email = scanner.nextLine();
        if (email != null && !email.trim().isEmpty()) {
            if (!checkEmail(email)) {
                System.err.println("FORMATO NON VALIDO\nL'EMAIL NON VERRA' MODIFICATA!!");
            } else {
                persona.setEmail(email);
            }
        }
        System.out.println("Contatto Aggiornato!");
        String verbo = "visualizzare";
        getOne(i, verbo);
    }

    @Override
    public void deleteOne(int i) {
        this.deleteRubrica(i);
        endOperation();
    }

    @Override
    public void searchByName() {
        scanner.nextLine();
        System.out.println("Inserisci il Nome che vuoi cercare");
        String firstName = scanner.nextLine();
        ArrayList<Contact> searchResult = this.searchByName(firstName);
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
        endOperation();
    }

    @Override
    public void endOperation() {
        System.out.println("\n-------------------------\n1)Fai altre operazioni\n0)ESCI");
        int num = scanner.nextInt();
        switch (num) {
            case 0:
                System.out.println("Grazie e Arrivederci!!!");
                break;
            case 1:
                mainMenu();
                break;
        }
    }

    @Override
    public void updateOwner() {
        System.out.println("VISUALIZZA E MODIFICA INFORMAZIONI UTENTE");
        this.getOwner().printAll();
        System.out.println("Vuoi modificare le informazioni?\nNUMERO QUALSIASI)SI\n0)NO");
        int num = scanner.nextInt();
        if (num > 0) {
            User owner = this.getOwner();
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
                if (!checkNumber(number)) {
                    System.err.println("FORMATO NON VALIDO\nIL NUMERO NON VERRA' MODIFICATO");
                } else {
                    owner.setNumber(number);
                }
            }
            System.out.println("Inserire la nuova email:");
            String email = scanner.nextLine();
            if (email != null && !email.trim().isEmpty()) {
                if (!checkEmail(email)) {
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
        endOperation();
    }
    /**
     * Controlla la formattazione della String numero inserita dall'utente
     *
     * @param number Stringa inserita in input dall'utente
     * @return boolean true = Il formato è corretto
     * false= Il formato è errato
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
     *
     * @param email Stringa inserita in input dall'utente
     * @return boolean true = Il formato è corretto
     * false= Il formato è errato
     */
    static boolean checkEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}

