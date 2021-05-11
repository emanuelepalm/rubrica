import people.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("Benvenuto Nella Tua rubrica!\n-------------------------\n");
        AddressBook addressBook = AddressBook.getInstance();
        addressBook.setOwner(User.createOwner());
        addressBook.start();
    }
}