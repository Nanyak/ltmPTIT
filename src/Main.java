import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException, SocketException {
//        Socket client = new Socket("10.24.0.100", 2207);
//        System.out.println(client);
//        //nhap 2 so nguyen
//        DataInputStream dis = new DataInputStream(client.getInputStream());
//        int a = dis.readInt();
//        int b = dis.readInt();
//        //
//        int sum = a+b;
//        //
//        DataOutputStream dos = new DataOutputStream(client.getOutputStream());
//        dos.writeInt(sum);
//        dis.close();
//        dos.close();
//        client.close();
        DatagramSocket server = new DatagramSocket(2207);
        System.out.println("server running on port 2207");
        while(true){
            byte[] buff = new byte[1024];
            DatagramPacket dpRequest = new DatagramPacket(buff, buff.length);
            server.receive(dpRequest);
            String strReq = new String(dpRequest.getData(), 0 , dpRequest.getLength());
            String strRes= new StringBuilder(strReq).reverse().toString();
            byte[] data = strRes.getBytes();
            DatagramPacket dpResponse = new DatagramPacket(data, data.length, dpRequest.getAddress(), dpRequest.getPort());
            server.send(dpResponse);
        }

    }
}