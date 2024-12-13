import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class DataIOputStream {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("203.162.10.109", 2207);
            socket.setSoTimeout(5000);

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

            String studentId = "B21DCCN208";
            String qCode = "380R3ecE";
            String messageToSend = studentId + ";" + qCode;

            dataOutputStream.writeUTF(messageToSend);
            dataOutputStream.flush();

            int a = dataInputStream.readInt();
            int b = dataInputStream.readInt();
            System.out.println("Received two integers from server: " + a + ", " + b);

            int sum = a + b;
            int product = a * b;

            dataOutputStream.writeInt(sum);
            System.out.println("Sending sum to server: " + sum);
            dataOutputStream.writeInt(product);
            System.out.println("Sending product to server: " + product);
            dataOutputStream.flush();

            dataOutputStream.close();
            dataInputStream.close();
            socket.close();
            System.out.println("Connection closed.");
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
