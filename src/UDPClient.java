import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        DatagramSocket client = new DatagramSocket();
        byte[] data = "abc xyz jfk".getBytes();
        DatagramPacket dpRequest = new DatagramPacket(data, data.length, InetAddress.getByName("localhost"), 2207);
        client.send(dpRequest);
        byte[] buff = new byte[1024];
        DatagramPacket dpResponse = new DatagramPacket(buff, buff.length);
        client.receive(dpResponse);
        String strReverse = new String(dpResponse.getData(), 0, dpResponse.getLength());
        System.out.println(strReverse);
        client.close();
    }
}
