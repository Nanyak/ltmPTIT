package TCP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TCP_CharacterStream_clO {
    public static void main(String[] args) throws Exception{
        String ma = "B22DCCN809;clO7hwtG";
        String ip = "203.162.10.109";
        int port = 2208;
        Socket socket = new Socket(ip,port);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        out.write(ma);
        out.newLine();
        out.flush();
        String s = in.readLine();
        String[] parts = s.split("\\s+");
        List<String> wordList = Arrays.asList(parts);
        wordList.sort(Comparator.comparingInt(String::length));
        String res = String.join(", ", wordList);
        out.write(res);
        out.newLine();
        out.flush();
        in.close();
        out.close();
        socket.close();

    }


}
