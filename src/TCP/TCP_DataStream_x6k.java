package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Arrays;
//[Mã câu hỏi (qCode): x6ktPOiE].  Một chương trình server cho phép kết nối qua TCP tại cổng 2207 (hỗ trợ thời gian liên lạc tối đa cho mỗi yêu cầu là 5 giây). Yêu cầu xây dựng chương trình client thực hiện giao tiếp với server sử dụng luồng data (DataInputStream/DataOutputStream) để trao đổi thông tin theo thứ tự:
//a. Gửi mã sinh viên và mã câu hỏi theo định dạng "studentCode;qCode".
//Ví dụ: "B10DCCN001;A1B2C3D4"
//b. Nhận một số nguyên hệ thập phân từ server. Ví dụ: 255
//c. Chuyển đổi số nguyên nhận được sang hai hệ cơ số 8 và 16. Gửi lần lượt chuỗi kết quả lên server. Ví dụ: Với số 255 hệ thập phân, kết quả gửi lên sẽ là hai chuỗi "377" và "FF".
//d. Đóng kết nối và kết thúc chương trình.
//note: trả về server dạng octal;hex
public class TCP_DataStream_x6k {
    public static void main(String[] args) throws Exception{
        String ma ="B22DCCN809;x6ktPOiE";
        int port = 2207;
        String ip = "203.162.10.109";
        Socket socket = new Socket(ip, port);
        socket.setSoTimeout(5000); // timeout 5s
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(ma);
        out.flush();
        int a = in.readInt();
        System.out.println(a);
        String octa = Integer.toOctalString(a).trim();
        String hex = Integer.toHexString(a).trim().toUpperCase();
        System.out.println(octa);
        System.out.println(hex);
        out.writeUTF(octa+";"+hex);  // gửi octal
        out.flush();

//        in.close();
//        out.close();
//        socket.close();

    }
}
