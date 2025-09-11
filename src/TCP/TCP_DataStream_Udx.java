package TCP;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCP_DataStream_Udx {
    public static void main(String[] args) throws Exception {
        String ma = "B22DCCN809;UdXFgcuf";
        int port = 2207;
        String ip = "203.162.10.109";
        Socket socket = new Socket(ip, port);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(ma);
        out.flush();
        int a = in.readInt();
        int b = in.readInt();
        int sum = a+b;
        int product =a*b;
        out.writeInt(sum);
        out.writeInt(product);
        out.flush();

    }
}
