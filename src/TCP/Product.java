package TCP;

import java.io.Serializable;

public class Product implements Serializable {
    private static final long serialVersionUID = 20231107;
    public int id;
    public String name;
    public double price;
    public int discount;

    public Product(String name, int id, double price, int discount) {
        this.name = name;
        this.id = id;
        this.price = price;
        this.discount = discount;
    }
}
