package FinalPractice;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;

public class UDPDataType {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2207;
            socket = new DatagramSocket();

            String studentId = "B21DCCN208";
            String qCode = "twob982x";
            String messageToSend = ";" + studentId + ";" + qCode;

            byte[] sendBuffer = messageToSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String response = new String(receivePacket.getData()).trim();
            System.out.println("Response from server: " + response);

            String[] responseParts = response.split(";");
            String requestId = responseParts[0];
            System.out.println("Request ID: " + requestId);

            List<Integer> data = Arrays.stream(responseParts[1].split(",")).map(Integer::parseInt).toList();
            int max = Collections.max(data);
            int min = Collections.min(data);
            StringBuilder res = new StringBuilder(requestId);
            res.append(";").append(max).append(",").append(min);
            System.out.println("res " + res);
            sendBuffer = res.toString().getBytes();
            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);

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
