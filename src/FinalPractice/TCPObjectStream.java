package FinalPractice;

import TCP.Customer;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TCPObjectStream {
    public static void main(String[] args) {
        Socket socket = null;
        String host = "203.162.10.109";
        int port = 2209;
        String studentCode = "B21DCCN208";
        String qCode = "AClckElK";
        String messageToSend = studentCode + ";" + qCode;
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());

            objectOutputStream.writeObject(messageToSend);
            objectOutputStream.flush();

            Customer customer = (Customer) objectInputStream.readObject();
            System.out.println("Received customer: " + customer.toString());
            customer.process();

            objectOutputStream.writeObject(customer);
            objectOutputStream.flush();

            objectInputStream.close();
            objectOutputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } 
    }
}

