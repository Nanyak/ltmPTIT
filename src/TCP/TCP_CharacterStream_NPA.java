package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCP_CharacterStream_NPA {
    public static String chuanhoa(String s) {
        if (s == null || s.isEmpty()) return "";
        StringBuilder sb = new StringBuilder(s).reverse();
        StringBuilder res = new StringBuilder();
        char[] arr = sb.toString().toCharArray();
        int cnt = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) {
                cnt++;
            } else {
                res.append(arr[i-1]);
                if (cnt > 1) res.append(cnt);
                cnt = 1;
            }
        }
        res.append(arr[arr.length-1]);
        if (cnt > 1) res.append(cnt);
        return res.toString();
    }
    public static void main(String[] args) throws Exception{
        String ma = "B22DCCN809;NPAzxxaA";
        int port = 2208;
        String ip = "203.162.10.109";
        Socket socket = new Socket(ip,port);
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out.write(ma);
        out.newLine();
        out.flush();
        String s = in.readLine();
        System.out.println(s);
        String[] parts = s.split("\\s+");
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            res.append(chuanhoa(parts[i]));
            if (i < parts.length - 1) res.append(" "); // thêm khoảng trắng giữa các từ
        }
        out.write(res.toString());
        out.newLine();
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
