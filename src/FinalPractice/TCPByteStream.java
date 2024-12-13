package FinalPractice;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPByteStream {
    public static void main(String[] args) {
        Socket socket = null;
        String host = "203.162.10.109";
        int port = 2206;
        String studentCode = "B21DCCN208";
        String qCode = "O274t9f2";
        String messageToSend = studentCode + ";" + qCode;
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            os.write(messageToSend.getBytes());
            os.flush();

            byte[] buffer = new byte[1024];
            is.read(buffer);
            String messageReceived = new String(buffer).trim();
            System.out.println("Received data from server: " + messageReceived);

            String[] numbers = messageReceived.split(",");
            int max = Integer.MIN_VALUE;
            int secondMax = Integer.MIN_VALUE;
            int maxIndex = -1;
            int secondMaxIndex = -1;
            for (int i = 0; i < numbers.length; i++) {
                int number = Integer.parseInt(numbers[i]);
                if (number > max) {
                    secondMax = max;
                    secondMaxIndex = maxIndex;
                    max = number;
                    maxIndex = i;
                } else if (number > secondMax) {
                    secondMax = number;
                    secondMaxIndex = i;
                }
            }

            String response = secondMax + "," + secondMaxIndex;
            System.out.println("Response to server: " + response);
            os.write(response.getBytes());
            os.flush();

            is.close();
            os.close();
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
