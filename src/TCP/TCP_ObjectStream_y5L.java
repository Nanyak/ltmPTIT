package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCP_ObjectStream_y5L {
    public static int funct (double price){
        int integerPart = (int) price;
        int discount = 0;
        while (integerPart > 0){
            discount += integerPart % 10;
            integerPart /= 10;
        }
        return discount;
    }
    public static void main(String[] args) throws Exception{
        String ma = "B22DCCN809;y5LWj18k";
        int port = 2209;
        String ip = "203.162.10.109";
        Socket socket = new Socket(ip, port);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(ma);
        out.flush();
        Product prod = (Product) in.readObject();
        System.out.println(prod);
        prod.discount = funct(prod.price);
        out.writeObject(prod);
        out.flush();
        in.close();
        out.close();
        socket.close();

    }
}
