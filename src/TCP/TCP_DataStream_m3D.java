package TCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class TCP_DataStream_m3D {
    public static void main(String[] args) throws Exception{
        String ma = "B22DCCN809;m3DzsIiN";
        int port = 2207;
        String ip = "203.162.10.109";
        Socket socket = new Socket(ip, port);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        out.writeUTF(ma);
        out.flush();
        String s = in.readUTF();
        String[] parts = s.split(",");
        int[] arr = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            arr[i] = Integer.parseInt(parts[i].trim());
        }
        int changes = 0;
        int totalVariation = 0;
        if (arr.length > 1) {
            // Xác định xu hướng ban đầu
            int prevDiff = arr[1] - arr[0];
            totalVariation += Math.abs(prevDiff);

            for (int i = 1; i < arr.length - 1; i++) {
                int diff = arr[i + 1] - arr[i];
                totalVariation += Math.abs(diff);

                // Nếu prevDiff và diff khác dấu => đổi chiều
                if ((prevDiff > 0 && diff < 0) || (prevDiff < 0 && diff > 0)) {
                    changes++;
                }

                // Cập nhật prevDiff nếu diff != 0
                if (diff != 0) {
                    prevDiff = diff;
                }
            }
        }
        out.writeInt(changes);
        out.writeInt(totalVariation);
        out.flush();
        in.close();
        out.close();
        socket.close();
    }
}
