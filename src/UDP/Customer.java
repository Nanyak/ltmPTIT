package UDP;

import java.io.Serializable;

public class Customer implements Serializable {
    private static final long serialVersionUID = 20151107;
    public String id;
    public String code;
    public String name;
    public String dayOfBirth;
    public String username;

    public Customer(String id, String code, String dayOfBirth, String name) {
        this.id = id;
        this.code = code;
        this.dayOfBirth = dayOfBirth;
        this.name = name;
    }
}
