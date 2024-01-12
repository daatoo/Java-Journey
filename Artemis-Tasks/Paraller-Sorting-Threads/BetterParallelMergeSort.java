package pgdp.threads;

import java.util.concurrent.RecursiveAction;

public class BetterParallelMergeSort extends RecursiveAction {
    private final int BOUNDARY = 10000;

    private Comparable[] helper;
    private Comparable[] array;
    private int low;
    private int high;

    public BetterParallelMergeSort(Comparable[] array) {
        this.helper = new Comparable[array.length];

        this.array = array;
        this.low = 0;
        this.high = array.length - 1;
    }

    public BetterParallelMergeSort(Comparable[] array, Comparable[] helper, int low, int high) {
        this.helper = helper;
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (low < high) {
            final int middle = (low + high) / 2;

            BetterParallelMergeSort leftSide = new BetterParallelMergeSort(array, helper, low, middle);
            BetterParallelMergeSort rightSide = new BetterParallelMergeSort(array, helper, middle + 1, high);

            int l = high - low + 1;
            if (l > BOUNDARY) {
                // Parallel
                leftSide.fork();
                rightSide.fork();

                leftSide.join();
                rightSide.join();
            } else {
                // Sequential Sort
                leftSide.compute();
                rightSide.compute();
            }

            merge(array, helper, low, middle, high);
        }
    }


    public static void merge(final Comparable[] array, final Comparable[] helper, final int low, final int middle, final int high) {
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft].compareTo(helper[helperRight]) <= 0) {
                array[current] = helper[helperLeft++];
            } else {
                array[current] = helper[helperRight++];
            }
            current++;
        }

        while (helperLeft <= middle) {
            array[current++] = helper[helperLeft++];
        }
    }
}