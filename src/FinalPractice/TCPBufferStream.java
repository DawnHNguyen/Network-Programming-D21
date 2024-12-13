package FinalPractice;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class TCPBufferStream {
    public static void main(String[] args) {
        Socket socket = null;
        String host = "203.162.10.109";
        int port = 2208;
        String studentCode = "B21DCCN208";
        String qCode = "O1Srs2aU";
        String messageToSend = studentCode + ";" + qCode;
        try {
            socket = new Socket(host, port);
            socket.setSoTimeout(5000);

            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            bw.write(messageToSend);
            bw.newLine();
            bw.flush();

            String received = br.readLine();
            List<String> data = Arrays.stream(received.split("\\s+")).sorted().toList();
            StringBuilder res = new StringBuilder();
            for (String s : data) {
                res.append(s).append(" ");
            }
            System.out.println(res);
            bw.write(res.toString().trim());

            bw.close();
            br.close();
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
