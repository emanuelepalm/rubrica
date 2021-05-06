public class AddressBook {
    People[] rubrica;
    private int indexMax;

    public AddressBook(int i) {
        this.rubrica = new People[i];
    }
    public AddressBook() {
        this.rubrica = new People[50];
    }

    public int getIndexMax(People[] agenda) {
        for(int i = 0; i < agenda.length; i++ ) {
            if(agenda[i] != null) {
                this.indexMax = 0;
            }
            else {
                this.indexMax = i;
                return this.indexMax;
            }
        }
        return this.indexMax;
    }
    public void setIndexMax(int indexMax) {
        this.indexMax = indexMax;
    }

    public void populate () {
        for(int i = 0; i < 10; i++) {
            People persona = new People();
            this.rubrica[i] = persona.populate();
        }
    }
    public People[] getRubricaAll() {
        return this.rubrica;
    }

    public People getRubrica(int i) {
        return this.rubrica[i];
    }

    public void deleteRubrica(int i) {
        indexMax = this.getIndexMax(rubrica);
        this.rubrica[i] = null;
        rubrica = this.getRubricaAll();
        for(int j = i + 1; j < indexMax; j++) {
            this.rubrica[j-1] = this.rubrica[j];
        }
        this.rubrica[indexMax-1] = null;
    }
    public void insertPersona(People persona, int indexMax) {
        this.rubrica[indexMax] = persona;
    }

}