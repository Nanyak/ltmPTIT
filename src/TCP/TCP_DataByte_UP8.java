package TCP;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class TCP_DataByte_UP8 {
    public static void main(String[] args) throws Exception{
        String ma= "B22DCCN809;UP8iMJIL";
        int port = 2206;
        String ip = "203.162.10.109";
        Socket socket = new Socket(ip, port);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        byte[] maGui = ma.getBytes();
        out.write(maGui);
        out.flush();
        byte[] buff = new byte[1024];
        int buffBytes = in.read(buff);
        String s = new String(buff, 0, buffBytes);
        String[] numStr = s.split(",");
        int[] nums = new int[numStr.length];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(numStr[i].trim());
        }
        int mean = 0;
        for (int value : nums) {
            mean += value;
        }
        mean = mean * 2/ nums.length;
        Arrays.sort(nums);
        int n1 = nums[0], n2 = nums[1];
        double minDiff = Double.MAX_VALUE;

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                double sum = nums[i] + nums[j];
                double diff = Math.abs(sum - mean);
                if (diff < minDiff) {
                    minDiff = diff;
                    n1 = nums[i];
                    n2 = nums[j];
                }
            }
        }
        String res = n1 +","+n2;
        out.write(res.getBytes());
        in.close();
        out.close();
        socket.close();
    }
}
