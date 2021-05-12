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
    public void exportRubrica();
    public void importRubrica();
    public void mock();

    public void updateOwner();
}
