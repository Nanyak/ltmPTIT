    package UDP;

    import java.net.DatagramPacket;
    import java.net.DatagramSocket;
    import java.net.InetAddress;

    public class UDP_DataType_mq1 {
        public static void main(String[] args) throws Exception{
            DatagramSocket socket = new DatagramSocket();
            InetAddress ip = InetAddress.getByName("203.162.10.109");
            int port = 2207;
            String ma = ";B22DCCN809;mq1FqHzH";
            byte[] buff = ma.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buff, buff.length, ip, port);
            socket.send(sendPacket);
            byte[] receiveData = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println(response);
            String[] parts = response.split(";");
            String requestId = parts[0];
            String[] numbersStr = parts[1].split(",");
            int[] numbers = new int[numbersStr.length];
            for (int i = 0; i < numbersStr.length; i++) {
                numbers[i] = Integer.parseInt(numbersStr[i].trim());
            }
            int max = numbers[0], min = numbers[0];
            for (int num : numbers) {
                if (num > max) max = num;
                if (num < min) min = num;
            }
            String result = requestId + ";" + max + "," + min;
            System.out.println(result);
            byte[] resultData = result.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(resultData, resultData.length, ip, port);
            socket.send(resultPacket);
            socket.close();

        }
    }