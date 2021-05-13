package json;

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
        for (int i = 1; i < peopleArr.length; i++) {
            peopleArrL.add(peopleArr[i]);
        }
        for (String persona : peopleArrL) {
            String[] peopleDataL = persona.split("\"");
            String uid = "";
            String firstName = "";
            String lastName = "";
            String number = "";
            String email = "";
            int numData = peopleDataL.length/4;
            for (int j = 1; j < peopleDataL.length; j += 4) {
                switch (peopleDataL[j].charAt(0)) {
                    case 'u':
                        uid = peopleDataL[j + 2];
                        break;
                    case 'f':
                        firstName = peopleDataL[j + 2];
                        break;
                    case 'l':
                        lastName = peopleDataL[j + 2];
                        break;
                    case 'n':
                        number = peopleDataL[j + 2];
                        break;
                    case 'e':
                        email = peopleDataL[j + 2];
                        break;
                }
                if ((j > 0) && (j == peopleDataL.length-4)) {
                    contacts.add(new Contatti(firstName, lastName, number, email, uid));
                    break;
                }
        /* for(int i = 0; i<(peopleArr.length-1); i++) {
                if(peopleArr[i].length()>2) {
                    appoggio.add(peopleArr[i]);
                }
            }
      */        }
            }
    /*
        }*/

         return contacts;
    }
}
