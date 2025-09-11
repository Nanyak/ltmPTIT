package TCP;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCP_ByteStream_pYO {
    public static void main(String[] args) throws Exception{
        String ma = "B22DCCN809;pYORMEXz";
        Socket socket = new Socket("203.162.10.109",2206);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        byte[] maGui = ma.getBytes();
        out.write(maGui);
        out.flush();
        byte[] buff = new byte[1024];
        int BytesRead = in.read(buff);
        String s = new String(buff, 0, BytesRead);
        String[] numbers = s.split("\\|");
        int res = 0;
        for(String number : numbers){
            int temp = Integer.parseInt(number.trim());
            res += temp;
        }
        String result = String.valueOf(res);
        out.write(result.getBytes());
        out.flush();
        in.close();
        out.close();
        socket.close();



    }
}
