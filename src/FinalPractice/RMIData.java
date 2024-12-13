package FinalPractice;

import RMI.DataService;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Arrays;

public class RMIData {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("203.162.10.109");
            DataService service = (DataService) registry.lookup("RMIDataService");

            String inputStr = (String) service.requestData("B21DCCN208", "qRxWW5wo");
            System.out.println(inputStr);
            int[] input = Arrays.stream(inputStr.split(", ")).mapToInt(Integer::parseInt).toArray();
            gen(input);
            service.submitData("B21DCCN208", "qRxWW5wo", Arrays.toString(input));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void gen(int[] a){
        int n = a.length;

        int i = n - 2;

        while (i >= 0 && a[i] >= a[i + 1]) --i;

        if (i >= 0){
            int j = n - 1;

            while (j > i && a[i] >= a[j]) --j;

            swap(a, i, j);
        } else {
            for (int j = 0; j < n; j++) {
                a[j] = j + 1;
            }
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }
}
