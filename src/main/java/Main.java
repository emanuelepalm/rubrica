import people.User;

public class Main {
    public static void main(String[] args) {
        System.out.println("Benvenuto Nella Tua rubrica!\n-------------------------\n");
        User owner = User.createOwner();
        AddressBook addressBook = new AddressBook(owner);
        addressBook.start();
    }
}