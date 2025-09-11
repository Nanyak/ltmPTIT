package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_String_zSm {
    public static void main(String[] args) throws Exception{
        DatagramSocket socket = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        int port = 2208;
        String ma = ";B22DCCN809;zSmCqZ9l";
        byte[] buff = ma.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(buff, buff.length, ip, port);
        socket.send(sendPacket);
        byte[] receiveData = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(receivePacket);
        String response = new String(receivePacket.getData(),0 , receivePacket.getLength());
        String[] parts = response.split(";",2);
        String rI = parts[0];
        String data = parts[1];
        String[] tmp = data.split("\\s+");
        data ="";
        for(String t : tmp){
            data += (Character.toUpperCase(t.charAt(0)) + t.substring(1).toLowerCase().trim())+ " ";
        }
        String res = rI + ";" + data;
        System.out.println(res);
        byte[] getData = res.getBytes();
        DatagramPacket sendPacket2 = new DatagramPacket(getData, getData.length, ip, port);
        socket.send(sendPacket2);
        socket.close();

    }
}
