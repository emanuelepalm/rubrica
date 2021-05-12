import people.Contatti;

import java.util.ArrayList;

public class JsonHandler {
    private String json;

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public void generateArray() {
        ArrayList<Contatti> contacts = new ArrayList<Contatti>(1);
        for(int i = 0; i < 10; i++) {
            contacts.add(Contatti.populate());
        }
        this.setJson(writeJson(contacts));
    }

    public static String writeJson(ArrayList<Contatti> people) {
        String json = "{rubrica:[ ";
        for(int i = 0; i < people.size(); i++) {
            json += "{\"uid\":\""+ people.get(i).getId() +"\",";
            json += "\"firstName\":\""+ people.get(i).getFirstName() +"\",";
            json += "\"lastName\":\""+ people.get(i).getLastName() +"\",";
            json += "\"number\":\""+ people.get(i).getNumber() +"\",";
            json += "\"email\":\""+ people.get(i).getEmail() +"\"}";
            if(i < (people.size()-1)) {
                json += ",";
            }
        }
        json +="]}";
        return json;
    }

    public static ArrayList<Contatti> convertJson(String json) {
        ArrayList<Contatti> contacts = new ArrayList<Contatti>();
        ArrayList<String> peopleArrL = new ArrayList<String>();
        ArrayList<String> appoggio = new ArrayList<String>();
        String[] peopleArr = json.split("\\[");
        peopleArr = peopleArr[1].split("\\{");
        for(int i = 1; i < peopleArr.length; i++) {
            peopleArrL.add(peopleArr[i]);
        }
        for (String persona:peopleArrL) {
            peopleArr = persona.split("\"");
            for(int i = 0; i<(peopleArr.length-1); i++) {
                if(peopleArr[i].length()>2) {
                    appoggio.add(peopleArr[i]);
                }
            }
            
        }
        String uid = "";
        String firstName = "";
        String lastName = "";
        String number = "";
        String email = "";
            for(int j = 0; j < appoggio.size(); j+=2) {

                    switch(appoggio.get(j).charAt(0)) {
                        case 'u':
                            uid = appoggio.get(j+1);
                            break;
                        case 'f':
                            firstName = appoggio.get(j+1);
                            break;
                        case 'l':
                            lastName = appoggio.get(j+1);
                            break;
                        case 'n':
                            number = appoggio.get(j+1);
                            break;
                        case 'e':
                            email = appoggio.get(j+1);
                            break;
                }
                if((j > 0) && (j % 5 ==0)) {
                    contacts.add(new Contatti(firstName, lastName, number, email, uid));
                }

        }
        return contacts;

    }

}
