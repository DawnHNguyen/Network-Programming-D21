package FinalPractice;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;


public class UDPString {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2208;
            socket = new DatagramSocket();

            String studentId = "B21DCCN208";
            String qCode = "2cMM3TIy";
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
            String data = responseParts[1];
            StringBuilder res = new StringBuilder(requestId);
            res.append(";");
            Map<Character, Integer> mp = new HashMap<>();
            for (Character c : data.toCharArray()) {
                if (mp.containsKey(c)){
                    mp.put(c, mp.get(c) + 1);
                } else mp.put(c, 1);
            }

            for (Character c : data.toCharArray()) {
                if (mp.containsKey(c)) {
                    res.append(mp.get(c)).append(c);
                    mp.remove(c);
                }
            }
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
