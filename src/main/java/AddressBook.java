import java.util.ArrayList;

public class AddressBook {

    private ArrayList<People> rubrica = new ArrayList<People>(10);
    private User owner;

    public AddressBook(User owner) {
        this.owner = owner;
    }
    public User getOwner() {
        return owner;
    }
    public void populate (int i) {
        for(int j = 0; j < i; j++) {
            People persona = People.populate();
            this.rubrica.add(j, persona);
        }
    }
    public ArrayList<People> getAll() {
        return this.rubrica;
    }

    public People getRubrica(int i) {
        return this.rubrica.get(i);
    }

    public void deleteRubrica(int i) {
        this.rubrica.remove(i);
    }

    public void insertPersona(People persona) {
        this.rubrica.add(persona);
    }

    public ArrayList<People> searchByName(String firstName, AddressBook addressBook) {
        ArrayList<People> searchResult = new ArrayList<People>(10);
        for(int i = 0; i < addressBook.rubrica.size(); i++ ) {
            if(addressBook.rubrica.get(i).equals(firstName)) {
                searchResult.add(i, addressBook.rubrica.get(i));
            }
        }
        return searchResult;
    }
}
