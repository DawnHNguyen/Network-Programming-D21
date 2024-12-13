import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPString {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2208;
            socket = new DatagramSocket();

            String studentId = "B21DCCN208";
            String qCode = "fJbItO8X";
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
            System.out.println("Request ID: " + requestId);

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
