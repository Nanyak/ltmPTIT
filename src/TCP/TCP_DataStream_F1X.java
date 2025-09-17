package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class TCP_DataStream_F1X {
    public static void main(String[] args) throws Exception{
        String ma = "B22DCCN809;F1XhBt7r";
        int port = 2207;
        String ip = "203.162.10.109";
        Socket socket = new Socket(ip,port);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(ma);
        out.flush();
        int k = in.readInt();
        String s = in.readUTF();
        System.out.println(s);
        String[] parts = s.split(",");
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> res = new ArrayList<>();
        for (int i =0;i<parts.length;i++){
            temp.add(parts[i]);
            if((i+1) % k == 0){
                Collections.reverse(temp);
                res.addAll(temp);

                temp.clear();
            }
        }
        if (!temp.isEmpty()) {
            Collections.reverse(temp);
            res.addAll(temp);
        }
        String result = String.join(",", res);
        System.out.println(result);
        out.writeUTF(result);
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
