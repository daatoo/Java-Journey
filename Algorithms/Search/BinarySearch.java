import java.util.Arrays;

public class BinarySearch {
    public static int binarySearch(int[] array, int target) {
        // use polymorphism to omit left and right params on initial call
        return binarySearch(array, target, 0, array.length - 1);
    }

    public static int binarySearch(int[] array, int target, int left, int right) {
        int middle = (right + left) / 2;

        if (right >= left) {
            if (array[middle] == target)
                return middle;

            if (array[middle] > target)
                return binarySearch(array, target, left, middle - 1);

            if (array[middle] < target)
                return binarySearch(array, target, middle + 1, right);
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {12, 22, 25, 36, 77, 90};
        int target = 22;
        int invalidTarget = 13;

        // test 1
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);

        int index1 = binarySearch(arr, target);
        if (index1 == -1)
            System.out.println("Element is not present in array");
        else
            System.out.println("Element is present at index " + index1);

        // test 2
        System.out.println("Target: " + invalidTarget);

        int index2 = binarySearch(arr, invalidTarget);
        if (index2 == -1)
            System.out.println("Element is not present in array");
        else
            System.out.println("Element is present at index " + index2);
    }
}
