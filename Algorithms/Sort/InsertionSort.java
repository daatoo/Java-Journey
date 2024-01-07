import java.util.Arrays;

public class InsertionSort {
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        int[] arrOddLength = {64, 34, 25, 12, 22, 11, 90};
        int[] arrEvenLength = {-24, 37, 55, 2, 11, 50};

        // test 1
        System.out.println("Original array: " + Arrays.toString(arrEvenLength));
        insertionSort(arrEvenLength);
        System.out.println("Sorted array: " + Arrays.toString(arrEvenLength) + "\n");

        // test 2
        System.out.println("Original array: " + Arrays.toString(arrOddLength));
        insertionSort(arrOddLength);
        System.out.println("Sorted array: " + Arrays.toString(arrOddLength));
    }
}