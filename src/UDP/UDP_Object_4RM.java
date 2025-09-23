package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Object_4RM {
    public static String taoEmail (String name){
        String[] parts = name.split("\\s+");
        String res = "";
        for (int i=0;i< parts.length - 1;i++){
            res += Character.toLowerCase(parts[i].charAt(0));
        }
        res = parts[parts.length - 1].toLowerCase() + res;
        res = res + "@ptit.edu.vn";
        return res;
    }
    public static String taoName (String name){
        String[] parts = name.split("\\s+");
        String res = "";
        for (int i=0;i< parts.length;i++){
            parts[i] = Character.toUpperCase(parts[i].charAt(0)) + parts[i].substring(1).toLowerCase();
        }
        res = String.join(" ",parts);
        return res;
    }
    public static void main(String[] args) throws Exception{
        String ma = ";B22DCCN809;4RMjP2Pk;";
        int port = 2209;
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket gui = new DatagramPacket(ma.getBytes(), ma.length(), ip, port);
        socket.send(gui);
        byte buff[] = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buff, buff.length);
        socket.receive(nhan);
        String rq = new String(nhan.getData(),0,8);
        ByteArrayInputStream input = new ByteArrayInputStream(nhan.getData(),8,nhan.getLength());
        ObjectInputStream in = new ObjectInputStream(input);
        Student student = (Student) in.readObject();
        student.email = taoEmail(student.name);
        student.name = taoName(student.name);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(output);
        out.writeObject(student);
        out.flush();
        byte gui2[] = new byte[8 + output.size()];
        System.arraycopy(rq.getBytes(), 0,gui2,0,8);
        System.arraycopy(output.toByteArray(),0,gui2,8,output.size());
        DatagramPacket res = new DatagramPacket(gui2, gui2.length,ip,port);
        socket.send(res);
    }
}
