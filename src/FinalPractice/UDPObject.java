package FinalPractice;

import UDP.Customer;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPObject {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            InetAddress serverAddress = InetAddress.getByName("203.162.10.109");
            int serverPort = 2209;
            socket = new DatagramSocket();

            String studentId = "B21DCCN208";
            String qCode = "fozPTOkF";
            String messageToSend = ";" + studentId + ";" + qCode;

            byte[] sendBuffer = messageToSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);

            byte[] receiveBuffer = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);

            byte[] requestIdBytes = new byte[8];
            System.arraycopy(receiveBuffer, 0, requestIdBytes, 0, 8);
            String requestId = new String(requestIdBytes).trim();
            System.out.println("Request ID: " + requestId);

            byte[] customerData = new byte[receivePacket.getLength() - 8];
            System.arraycopy(receiveBuffer, 8, customerData, 0, customerData.length);
            ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(customerData));
            Customer customer = (Customer) ois.readObject();
            ois.close();

            customer.process();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(customer);
            oos.flush();
            byte[] resData = baos.toByteArray();
            byte[] finalResData = new byte[8 + resData.length];
            System.arraycopy(requestIdBytes, 0, finalResData, 0, 8);
            System.arraycopy(resData, 0, finalResData, 8, resData.length);
            sendPacket = new DatagramPacket(finalResData, finalResData.length, serverAddress, serverPort);
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
