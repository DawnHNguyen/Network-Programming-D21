import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[] a = {3, 2, 1};
        gen(a);
        System.out.println(Arrays.toString(a));
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
