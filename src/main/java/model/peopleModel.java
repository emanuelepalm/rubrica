package model;

public enum peopleModel {
    UID("uid",1),
    FIRSTNAME("firstName",2),
    LASTNAME("lastName",3),
    NUMBER("number",4),
    EMAIL("email",5);

    private final String key;
    private final int value;

    peopleModel(String key, int value) {
        this.key = key;
        this.value = value;
    }
    public String getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    public static String getKeyFromValue(int num) {
        switch (num) {
            case 1:
                return UID.getKey();
            case 2:
                return FIRSTNAME.getKey();
            case 3:
                return LASTNAME.getKey();
            case 4:
                return NUMBER.getKey();
            case 5:
                return EMAIL.getKey();

        }
        return "ERRORE";
    }
}


