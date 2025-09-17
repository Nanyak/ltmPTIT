package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Socket;

//Address Object
public class TCP_ObjectStream_niC {
    public static String chuanHoaAddrLine(String addressLine){
        if(addressLine == null) return "";
        StringBuilder cleaned = new StringBuilder();
        for(char c : addressLine.toCharArray()){
            if(Character.isLetterOrDigit(c) || Character.isSpaceChar(c)){
                cleaned.append(c);
            }
        }
        String[] words = cleaned.toString().trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        for (String word : words) {
            if (word.isEmpty()) continue;
            result.append(Character.toUpperCase(word.charAt(0)));
            if (word.length() > 1) {
                result.append(word.substring(1).toLowerCase());
            }
            result.append(" ");
        }
        return result.toString().trim();
    }
    public static String chuanHoaPostalCode(String postalCode){
        if(postalCode == null) return "";
        StringBuilder cleaned = new StringBuilder();
        for(char c : postalCode.toCharArray()){
            if(Character.isDigit(c) || c == '-'){
                cleaned.append(c);
            }
        }
        return cleaned.toString().trim();
    }
    public static void main(String[] args) throws Exception{
        String ma = "B22DCCN809;niCBHi12";
        int port = 2209;
        String ip = "203.162.10.109";
        Socket socket = new Socket(ip, port);
        ObjectInputStream in= new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(ma);
        out.flush();

        Address addr = (Address)in.readObject();
        addr.setAddressLine(chuanHoaAddrLine(addr.getAddressLine()));
        System.out.println(addr.addressLine);
        addr.setPostalCode(chuanHoaPostalCode(addr.getPostalCode()));
        System.out.println(addr.postalCode);
        out.writeObject(addr);
        out.flush();
        out.close();
        in.close();
        socket.close();
        System.out.println(addr);

    }
}
