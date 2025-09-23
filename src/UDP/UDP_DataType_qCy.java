package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDP_DataType_qCy {
    public static void main(String[] args) throws Exception {
        String ma = ";B22DCCN809;qCywN7fC";
        InetAddress ip = InetAddress.getByName("203.162.10.109");
        int port = 2207;
        DatagramSocket socket = new DatagramSocket();

        // a. Gửi mã
        byte[] maGui = ma.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(maGui, maGui.length, ip, port);
        socket.send(sendPacket);

        // b. Nhận thông điệp
        byte[] buff = new byte[4096];
        DatagramPacket receivePacket = new DatagramPacket(buff, buff.length);
        socket.receive(receivePacket);
        String s = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Server gửi về: " + s);

        // c. Tách requestId và danh sách số
        String[] parts = s.split(";");
        String requestId = parts[0];
        String[] numbers = parts[1].split(",");

        int[] nums = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            nums[i] = Integer.parseInt(numbers[i]);
        }
        Arrays.sort(nums);

        int secondMax = nums[nums.length - 2];
        int secondMin = nums[1];

        String res = requestId + ";" + secondMax + "," + secondMin;
        System.out.println("Kết quả gửi lên: " + res);

        // Gửi kết quả lên server
        byte[] resGui = res.getBytes();
        DatagramPacket resPacket = new DatagramPacket(resGui, resGui.length, ip, port);
        socket.send(resPacket);

        // d. Đóng socket
        socket.close();
    }
}
