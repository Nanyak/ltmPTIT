package UDP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.Period;
import java.util.Collections;

public class UDP_Object_bru {
    public static String chuanhoa1 (String name){
        String[] parts = name.split(" ");
        String temp = "";
        temp = parts[0];
        parts[0] = parts[parts.length - 1];
        parts[parts.length - 1] = temp;
        String res = String.join(" ", parts);
        return res;
    }
    public static int chuanhoa2 (int quantity){
        String s = Integer.toString(quantity);
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        return Integer.parseInt(sb.toString());
    }
    public static void main(String[] args) throws Exception{
        String ma = ";B22DCCN809;bru01n67";
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        int port = 2209;
        DatagramSocket socket = new DatagramSocket();
        DatagramPacket gui = new DatagramPacket(ma.getBytes(), ma.length(),ip,port);
        socket.send(gui);
        byte buff[] = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(buff, buff.length);
        socket.receive(nhan);
        String rq = new String(nhan.getData(),0,8);
        ByteArrayInputStream input = new ByteArrayInputStream(nhan.getData(),8, nhan.getLength());
        ObjectInputStream in = new ObjectInputStream(input);
        Product prod = (Product) in.readObject();
        prod.name = chuanhoa1(prod.name);
        prod.quantity = chuanhoa2(prod.quantity);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(output);
        out.writeObject(prod);
        out.flush();

        byte gui2[] = new byte[8+ output.size()];
        System.arraycopy(rq.getBytes(), 0, gui2, 0, 8 );
        System.arraycopy(output.toByteArray(), 0 ,gui2, 8, output.size());
        DatagramPacket guiLai = new DatagramPacket(gui2, gui2.length, ip ,port);
        socket.send(guiLai);
    }
}
