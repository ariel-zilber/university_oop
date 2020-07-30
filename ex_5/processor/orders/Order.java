package filesprocessing.processor.orders;

import java.io.File;
import java.util.Comparator;

/**
 * A sorter used for sorting files by absolute name , going from ’a’ to ’z’
 *
 * @author Ariel Zilbershteyin
 */
public abstract class Order implements Comparator<File> {

    /**
     * Sorts the provided files using the merge sort algorithm
     *
     * @param files the files to sort
     */
    public void sort(File[] files) {
        File[] tmpArr = new File[files.length];
        mergeSort(files, tmpArr, 0, files.length - 1);
    }


    /**
     * The mergeSort algorithm that was taught in introduction to CS.
     *
     * @param files      an array of File items.
     * @param tmpArr     an array to place the merged result.
     * @param leftIndex  the left most index of the sub array.
     * @param rightIndex the right most index of the sub array.
     */
    private void mergeSort(File[] files, File[] tmpArr, int leftIndex, int rightIndex) {

        if (leftIndex < rightIndex) {

            int centerIndex = (leftIndex + rightIndex) / 2;

            // recursive call on the left sub array
            mergeSort(files, tmpArr, leftIndex, centerIndex);

            // recursive call on the right sub array
            mergeSort(files, tmpArr, centerIndex + 1, rightIndex);

            // merges the sub arrays
            merge(files, tmpArr, leftIndex, centerIndex + 1, rightIndex);
        }
    }

    /**
     * merges sorted arrays.
     *
     * @param a             array of File items.
     * @param tmpArr        array to place the merged result.
     * @param leftPosition  left-most index of the subarray.
     * @param rightPosition index of the start of the second half.
     * @param rightEnd      right-most index of the subarray.
     */
    private void merge(File[] a, File[] tmpArr, int leftPosition, int rightPosition, int rightEnd) {
        int tmpPos = leftPosition;
        int leftEnd = rightPosition - 1;
        int elementNum = rightEnd - leftPosition + 1;

        while (leftPosition <= leftEnd && rightPosition <= rightEnd) {
            if (compare(a[leftPosition], a[rightPosition]) <= 0) {
                tmpArr[tmpPos++] = a[leftPosition++];
            } else {
                tmpArr[tmpPos++] = a[rightPosition++];
            }
        }

        // Copy the rest of left sub-array
        while (leftPosition <= leftEnd)
            tmpArr[tmpPos++] = a[leftPosition++];

        // Copy the rest of right sub-array
        while (rightPosition <= rightEnd)
            tmpArr[tmpPos++] = a[rightPosition++];

        // Copy the temporary array back
        for (int i = 0; i < elementNum; i++, rightEnd--)
            a[rightEnd] = tmpArr[rightEnd];
    }
}
