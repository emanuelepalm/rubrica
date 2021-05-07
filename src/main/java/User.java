public class User extends People {

    private String password;

    public User(String firstName, String lastName, String number, String email, String password) {
        super(firstName, lastName, number, email);
        this.password = password;
    }
    public static User createOwner() {
        String firstName = "Emanuele";
        String lastName = "Palmieri";
        String email = "palmieri.emanuele12@gmail.com";
        String number = "3384390361";
        String password = "password";
        User owner = new User(firstName,lastName,email,number,password);
        return owner;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public void setFirstName(String firstName) {
        super.setFirstName(firstName);
        System.out.println("Il tuo nome è stato modificato!");
    }

    @Override
    public void setLastName(String lastName) {
        super.setLastName(lastName);
        System.out.println("Il tuo cognome è stato modificato!");
    }

    @Override
    public void setNumber(String number) {
        super.setNumber(number);
        System.out.println("Il tuo numero è stato modificato!");
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
        System.out.println("La tua email è stato modificata!");
    }
    public void printAll() {
        System.out.println("Nome: " + this.getFirstName());
        System.out.println("Cognome: " + this.getLastName());
        System.out.println("Email: " + this.getEmail());
        System.out.println("Numero: " + this.getNumber());
        System.out.println("Password: " + this.getPassword());
    }
}
