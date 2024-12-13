import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UDPDataType {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2207;

            socket = new DatagramSocket();

            String studentId = "B21DCCN208";
            String qCode = "ZUOyO4Cl";
            String messageToSend = ";" + studentId + ";" + qCode;

            byte[] sendBuffer = messageToSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData());
            System.out.println("Response from server: " + response);

            String[] responseParts = response.split(";");
            String requestId = responseParts[0];
            String[] numbersStr = responseParts[1].split(",");

            int[] numbers = Arrays.stream(numbersStr).mapToInt(Integer::parseInt).toArray();
            int max = Arrays.stream(numbers).max().getAsInt();
            int min = Arrays.stream(numbers).min().getAsInt();
            System.out.println("Max: " + max + ", Min: " + min);

            String resultMessage = requestId + ";" + max + "," + min;
            byte[] resultBuffer = resultMessage.getBytes();
            DatagramPacket resultPacket = new DatagramPacket(resultBuffer, resultBuffer.length, serverAddress, serverPort);
            socket.send(resultPacket);
            System.out.println("Result sent to server: " + resultMessage);

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}
