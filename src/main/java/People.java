import java.util.Random;

public class People {
    private String firstName;
    private String lastName;
    private String number;
    private String email;

    public People() {
    }

    public People(String firstName, String number) {
        this.firstName = firstName;
        this.number = number;
    }
    public People(String firstName, String lastName, String number, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
    }
    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
       this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public static People populate() {
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
         People persona = new People(firstName, lastName,number ,email );
         return persona;


    }

}
