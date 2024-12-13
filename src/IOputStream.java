import java.io.*;
import java.net.Socket;

public class IOputStream {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("203.162.10.109", 2206);
            socket.setSoTimeout(5000);

            String studentId = "B21DCCN208";
            String qCode = "LWJJNIm0";
            String messageToSend = studentId + ";" + qCode;

            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();

            outputStream.write(messageToSend.getBytes());
            outputStream.flush();

            byte[] serverResponseByte = new byte[1024];
            inputStream.read(serverResponseByte);
            String serverResponse = new String(serverResponseByte).trim();
            System.out.println("Received data from server: " + serverResponse);

            String[] numberStrings = serverResponse.split("\\|");
            int sum = 0;
            for (String numberString : numberStrings) {
                sum += Integer.parseInt(numberString);
            }

            outputStream.write(String.valueOf(sum).getBytes());
            outputStream.flush();
            System.out.println("Sum sent to server: " + sum);

            inputStream.close();
            outputStream.close();
            socket.close();
            System.out.println("Connection closed.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
