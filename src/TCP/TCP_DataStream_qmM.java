package TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
//[Mã câu hỏi (qCode): qmMtJpCR].  Một chương trình server hỗ trợ kết nối qua giao thức TCP tại cổng 2206 (hỗ trợ thời gian giao tiếp tối đa cho mỗi yêu cầu là 5s). Yêu cầu xây dựng chương trình client thực hiện kết nối tới server sử dụng luồng byte dữ liệu (InputStream/OutputStream) để trao đổi thông tin theo thứ tự:
//a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
//Ví dụ: "B16DCCN999;C89DAB45"
//b. Nhận dữ liệu từ server là một chuỗi các số nguyên được phân tách bởi ký tự ",".
//Ví dụ: "8,4,2,10,5,6,1,3"
//c. Tính tổng của tất cả các số nguyên tố trong chuỗi và gửi kết quả lên server.
//Ví dụ: Với dãy "8,4,2,10,5,6,1,3", các số nguyên tố là 2, 5, 3, tổng là 10. Gửi lên server chuỗi "10".
//d. Đóng kết nối và kết thúc chương trình.
public class TCP_DataStream_qmM {
    public static boolean snt(int n){
        if(n <= 1){
            return false;
        }
        for(int i=2;i<= Math.sqrt(n);i++){
            if(n%i ==0){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) throws Exception{
        String ma = "B22DCCN809;qmMtJpCR";
        int port = 2206;
        String ip = "203.162.10.109";
        Socket socket = new Socket(ip, port);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        byte[] maGui = ma.getBytes();
        out.write(maGui);
        out.flush();
        byte buff[] = new byte[1028];
        int BytesRead = in.read(buff);
        String s = new String(buff, 0,BytesRead).trim();
        String[] nums = s.split(",");
        int sum = 0;
        for(String num : nums){
            int n = Integer.parseInt(num);
            if(snt(n)){
                sum += n;
            }
        }
        String res = String.valueOf(sum);
        out.write(res.getBytes());
        in.close();
        out.close();
        socket.close();
    }
}
