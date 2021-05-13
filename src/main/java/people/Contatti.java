package people;

import java.util.Random;

public class Contatti extends People {
    private String number;
    private String email;
    private String uid;


    public String getId() {
        return uid;
    }


    public Contatti(String firstName, String lastName, String number, String email, String uid) {
        super(firstName, lastName);
        this.email = email;
        this.number = number;
        this.uid = uid;
    }

    public Contatti(String firstName, String number) {
        super(firstName," ");
        this.number = number;
    }
    public Contatti(String firstName, String lastName, String number, String email) {
        super(firstName, lastName);
        this.email = email;
        this.number = number;
        this.uid = this.generateId(firstName, lastName);
    }

    public String generateId(String firstName, String lastName) {
        String id = "";
        Random random = new Random();
        for(int j = 0; j<6; j++) {
            char idChar = (char) (random.nextInt(26) + 'a');
            id += idChar;
        }
        if(lastName.length() > 0) {
            id += "-" + firstName.charAt(0) + lastName.charAt(0);
        } else {
            id += "-" +firstName.charAt(0)+"x";
        }
        return id;
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
       super.setFirstName(firstName);
    }
    @Override
    public String getLastName() {
        return super.getLastName();
    }
    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
    }
    public String getNumber() {
        return this.number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public static Contatti populate() {
         String[] firstNameAr = {"Mario","Luca","Pippo","Riccardo","Antonio","Gerardo","Antonello","Peppino","Rosario","Mario","Celestino"};
         String[] lastNameAr = {"Rossi","Bianchi","Palmieri","Antonelli","Tiberio","Duvall","Bernini","Rodriguez","Panzerelli","Ricciardi","Smith"};
         Random random = new Random();
         int i = random.nextInt(11);
         String firstName = firstNameAr[i];
         i = random.nextInt(11);
         String lastName = lastNameAr[i];
         String email = (Character.toLowerCase(firstName.charAt(0)) + firstName.substring(1))+ (Character.toLowerCase(lastName.charAt(0)) + lastName.substring(1)) + "@gmail.it";
         String number = "338";
         for (i = 0; i<7; i++) {
             number += Integer.toString(random.nextInt(9));
         }

         Contatti persona = new Contatti(firstName, lastName,number ,email);
         return persona;


    }

}
