package FinalPractice;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class TCPDataStream {
    public static void main(String[] args) {
        Socket socket = null;
        String host = "203.162.10.109";
        int port = 2207;
        String studentCode = "B21DCCN208";
        String qCode = "BCWrhNms";
        String messageToSend = studentCode + ";" + qCode;
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            dos.writeUTF(messageToSend);
            dos.flush();

            int n = dis.readInt();
            Map<Integer, Float> mp = new HashMap<>();
            for (int i = 1; i <= 6; i++) {
                mp.put(i, 0f);
            }

            int temp;
            for (int i = 0; i < n; i++) {
                temp = dis.readInt();
                mp.put(temp, mp.get(temp) + 1f);
            }

            for (int i = 1; i <= 6; i++) {
                dos.writeFloat(mp.get(i) / n);
                dos.flush();
            }

            dis.close();
            dos.close();
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
