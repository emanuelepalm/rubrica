import people.Contact;
import people.User;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * interfaccia Menu
*/
public interface Menu {

    public void start();
    public void mainMenu();
    public void getAllMenu();
    public void getOneMenu(String verbo);
    public void getOne(int index, String verbo);
    public void addOne();
    public void updateOne(int i);
    public void deleteOne(int i);
    public void searchByName();
    public void endOperation();


    public void updateOwner();
}
