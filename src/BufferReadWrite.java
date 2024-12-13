import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class BufferReadWrite {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("203.162.10.109", 2208);
            socket.setSoTimeout(5000);

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String studentId = "B21DCCN208";
            String qCode = "0W7ItPgu";
            String messageToSend = studentId + ";" + qCode;

            writer.write(messageToSend);
            writer.newLine();
            writer.flush();

            String serverResponse = reader.readLine();
            System.out.println("Received domain list from server: " + serverResponse);

            String[] domainList = serverResponse.split(", ");
            List<String> eduDomains = new ArrayList<>();
            for (String domain : domainList) {
                if (domain.endsWith(".edu")) {
                    eduDomains.add(domain);
                }
            }

            String resultToSend = String.join(", ", eduDomains);
            System.out.println("Sending .edu domains to server: " + resultToSend);

            writer.write(resultToSend);
            writer.newLine();
            writer.flush();

            writer.close();
            reader.close();
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
