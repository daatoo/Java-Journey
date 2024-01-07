import java.util.Arrays;

public class LinearSearch {
    public static int linearSearch(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {-64, 34, 25, 12, 22, 11, 90};
        int target = 22;
        int invalidTarget = 13;

        // test 1
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);

        int index1 = linearSearch(arr, target);
        System.out.println("Returned index: " + index1 + "\n");

        // test 2
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + invalidTarget);

        int index2 = linearSearch(arr, invalidTarget);
        System.out.println("Returned index: " + index2);
    }
}
