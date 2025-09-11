package UDP;

import javax.xml.crypto.Data;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_DataType_lW7 {
    public static void main(String[] args) throws Exception{
        String ma =";B22DCCN809;lW7zO9K1";
        int port = 2207;
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        DatagramSocket socket = new DatagramSocket();
        byte[] maGui = ma.getBytes();
        DatagramPacket gui = new DatagramPacket(maGui,maGui.length,ip, port);
        socket.send(gui);
        byte[] receiveData = new byte[1024];
        DatagramPacket nhan = new DatagramPacket(receiveData, receiveData.length);
        socket.receive(nhan);
        String response = new String(nhan.getData(), 0, nhan.getLength());
        System.out.println(response);
        String[] parts = response.split(";", 3);
        String rI = parts[0];
        String a = parts[1];
        String b = parts[2];
        BigInteger bA = new BigInteger(a);
        BigInteger bB = new BigInteger(b);
        BigInteger bSum = bA.add(bB);
        BigInteger bSub = bA.subtract(bB);
        String res = rI + ";" + bSum.toString() + "," + bSub.toString();
        System.out.println(res);
        byte[] sendRes = res.getBytes();
        DatagramPacket resSend = new DatagramPacket(sendRes, sendRes.length, ip, port);
        socket.send(resSend);
        socket.close();
    }
}
