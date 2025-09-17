package TCP;

import java.io.Serial;
import java.io.Serializable;

public class Address implements Serializable {
    static final long serialVersionUID = 20180801L;
//    id int, code String, addressLine String, city String, postalCode String
    public int id;
    public String code;
    public String addressLine;
    public String city;
    public String postalCode;

    public Address(String addressLine, int id, String code, String city, String postalCode) {
        this.addressLine = addressLine;
        this.id = id;
        this.code = code;
        this.city = city;
        this.postalCode = postalCode;
    }

    public int getId() {
        return id;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public String getCode() {
        return code;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

}
