import java.util.Arrays;

public class BubbleSort {
    public static void bubbleSort(int[] array) {
        int length = array.length;
        boolean swapped;

        for (int i = 0; i < length - 1; i++) {
            swapped = false;

            for (int j = 0; j < length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap arr[j] and arr[j+1]
                    int temporary = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temporary;
                    swapped = true;
                }
            }

            // If no two elements were swapped in the inner loop, the array is already sorted
            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] arrOddLength = {64, 34, 25, 12, 22, 11, 90};
        int[] arrEvenLength = {-24, 37, 55, 2, 11, 50};

        // test 1
        System.out.println("Original array: " + Arrays.toString(arrEvenLength));
        bubbleSort(arrEvenLength);
        System.out.println("Sorted array: " + Arrays.toString(arrEvenLength) + "\n");

        // test 2
        System.out.println("Original array: " + Arrays.toString(arrOddLength));
        bubbleSort(arrOddLength);
        System.out.println("Sorted array: " + Arrays.toString(arrOddLength));
    }
}