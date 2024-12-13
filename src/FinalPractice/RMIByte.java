package FinalPractice;

import RMI.ByteService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIByte {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("203.162.10.109");

            ByteService service = (ByteService) registry.lookup("RMIByteService");

            byte[] data = service.requestData("B21DCCN208", "5t3fSxSj");
            StringBuilder hexBuilder = new StringBuilder();
            for (byte b : data) {
                hexBuilder.append(String.format("%02x", b));
            }
            service.submitData("B21DCCN208", "5t3fSxSj", hexBuilder.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
