package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Object_yuF {
    public static String chuanhoa1 (String name){
        String[] parts = name.split("\\s+");
        String res = parts[parts.length-1].toUpperCase();
        res += ", ";
        for(int i=0;i< parts.length-1;i++){
            parts[i] = Character.toUpperCase(parts[i].charAt(0)) + parts[i].substring(1).toLowerCase();
            res += parts[i];
            if(i != parts.length - 2){
                res += " ";
            }
        }
        return res;
    }
    public static String chuanhoa2 (String dayOfBirth){
        String[] parts = dayOfBirth.split("-");
        String temp = "";
        temp = parts[0];
        parts[0] = parts[1];
        parts[1] = temp;
        String res = String.join("/",parts);
        return res;
    }
    public static String chuanhoa3(String name){
        String[] parts = name.split("\\s+");
        String res = "";
        for(int i=0;i< parts.length - 1;i++){
            res += parts[i].charAt(0);
        }
        res += parts[parts.length-1];
        return res;
    }
    public static void main(String[] args) throws Exception{
        String ma = ";B22DCCN809;yuFHx5EG";
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        int port = 2209;
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket gui = new DatagramPacket (ma.getBytes(), ma.length(), ip,port);
        socket.send(gui);
        byte[] buff = new byte[4096];
        DatagramPacket nhan = new DatagramPacket(buff, buff.length);
        socket.receive(nhan);
        String rq = new String(nhan.getData(), 0, 8);
        ByteArrayInputStream input = new ByteArrayInputStream(nhan.getData(),8,nhan.getLength());
        ObjectInputStream in = new ObjectInputStream(input);
        Customer cust = (Customer) in.readObject();
        String name = cust.name;
//        cust.name = chuanhoa1(name);
        cust.dayOfBirth = chuanhoa2(cust.dayOfBirth);
        cust.username = chuanhoa3(name);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(output);
        out.writeObject(cust);
        out.flush();
        byte[] gui2 = new byte[8 + output.size()];
        System.arraycopy(rq.getBytes(), 0,gui2, 0, 8);
        System.arraycopy(output.toByteArray(), 0, gui2, 8, output.size());
        DatagramPacket res  = new DatagramPacket(gui2, gui2.length,ip,port);
        socket.send(res);

    }
}
