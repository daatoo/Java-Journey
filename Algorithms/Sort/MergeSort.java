import java.util.Arrays;

public class MergeSort {
    public static void mergeSort(int[] array) {
        if (array == null || array.length == 1)
            return; // sort is not needed

        int len = array.length;
        int halfLength = len / 2;

        // create halves
        int[] leftHalf = new int[halfLength];
        int[] rightHalf = new int[len - halfLength];

        // populate arrays
        for (int i = 0; i < halfLength; i++) {
            leftHalf[i] = array[i]; // left
        }

        for (int j = halfLength; j < len; j++) {
            rightHalf[j - halfLength] = array[j]; // right
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        sort(array, leftHalf, rightHalf);
    }


    public static void sort(int[] array, int[] left, int[] right) {
        int leftLen = left.length;
        int rightLen = right.length;
        int i = 0, j = 0, k = 0; // i - left, j - right, k = array

        while (i < leftLen && j < rightLen) {
            if (left[i] <= right[j]) {
                array[k] = left[i];
                i++;
            }
            else {
                array[k] = right[j];
                j++;
            }
            k++;
        }

        // populate remaining at the left
        while (i < leftLen) {
            array[k] = left[i];
            k++;
            i++;
        }

        // populate remaining at the right
        while (j < rightLen) {
            array[k] = right[j];
            k++;
            j++;
        }
    }

    public static void main(String[] args) {
        int[] arrOddLength = {64, 34, 25, 12, 22, 11, 90};
        int[] arrEvenLength = {-24, 37, 55, 2, 11, 50};

        // test 1
        System.out.println("Original array: " + Arrays.toString(arrEvenLength));
        mergeSort(arrEvenLength);
        System.out.println("Sorted array: " + Arrays.toString(arrEvenLength) + "\n");

        // test 2
        System.out.println("Original array: " + Arrays.toString(arrOddLength));
        mergeSort(arrOddLength);
        System.out.println("Sorted array: " + Arrays.toString(arrOddLength));
    }
}