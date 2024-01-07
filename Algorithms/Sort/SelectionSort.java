import java.util.Arrays;

public class SelectionSort {
    public static void selectionSort(int[] array) {
        int length = array.length;

        for (int i = 0; i < length - 1; i++) {
            // Find the minimum element in the remaining unsorted array
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arrOddLength = {64, 34, 25, 12, 22, 11, 90};
        int[] arrEvenLength = {-24, 37, 55, 2, 11, 50};

        // test 1
        System.out.println("Original array: " + Arrays.toString(arrEvenLength));
        selectionSort(arrEvenLength);
        System.out.println("Sorted array: " + Arrays.toString(arrEvenLength) + "\n");

        // test 2
        System.out.println("Original array: " + Arrays.toString(arrOddLength));
        selectionSort(arrOddLength);
        System.out.println("Sorted array: " + Arrays.toString(arrOddLength));
    }
}