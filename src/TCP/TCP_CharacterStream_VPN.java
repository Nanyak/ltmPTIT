package TCP;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TCP_CharacterStream_VPN {
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("203.162.10.109",2208);
        String ma = "B22DCCN809;VPNSQW0E";
        BufferedWriter viet = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        viet.write(ma);
        viet.newLine();
        viet.flush();
        BufferedReader doc = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String s = doc.readLine();
        String[] domains = s.split(",\\s*");
        List<String> eduDomains = new ArrayList<>();
        for(String domain: domains){
            if(domain.endsWith(".edu")){
                eduDomains.add(domain.trim());
                System.out.println("Tên miền .edu tìm thấy: " + domain);
            }
        }
        if(!eduDomains.isEmpty()){
            String response = String.join(",", eduDomains);
            viet.write(response);
            viet.newLine();
            viet.flush();
        }
        viet.close();
        doc.close();
        socket.close();


    }
}
