package UDP;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 20171107;
    public String id;
    public String code;
    public String name;
    public String email;

    public Student(String code) {
        this.code = code;
    }

    public Student(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
