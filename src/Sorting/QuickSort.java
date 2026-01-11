package Sorting;

import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {1,2,3};
        int pivotIndex = 4; // can be any index
//        quickSort2(arr, 0, arr.length - 1);
        quickSort4(arr, 0, arr.length - 1);

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    private static int partition(int[] arr, int pivotIndex, int firstIndex, int lastIndex) {
        if (firstIndex == lastIndex) {
            return firstIndex;
        }

        int pivot = arr[pivotIndex];
        int i = firstIndex;
        int j = lastIndex;

        while (true) {
            // Move i right until arr[i] >= pivot (skip pivotIndex)
            while (i <= lastIndex && (arr[i] < pivot || i == pivotIndex)) {
                i++;
            }

            // Move j left until arr[j] <= pivot (skip pivotIndex)
            while (j >= firstIndex && (arr[j] > pivot || j == pivotIndex)) {
                j--;
            }

            if (i >= j) {
                break;
            }

            // Swap elements
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }

        // Finally place pivot in its correct position
        int temp = arr[j];
        arr[j] = pivot;
        arr[pivotIndex] = temp;

        return j; // Return pivot's new index
    }

    private static void quickSort(int[] arr, int pivotIndex, int firstIndex, int lastIndex) {
        if (firstIndex >= lastIndex) {
            return;
        }

        Random random = new Random();
        int sortedPosition = partition(arr, pivotIndex, firstIndex, lastIndex);

        if (sortedPosition > firstIndex) {
            int pivot1 = random.nextInt(firstIndex, sortedPosition + 1);
            quickSort(arr, pivot1, firstIndex, sortedPosition - 1);
        }
        if (sortedPosition < lastIndex) {
            int pivot2 = random.nextInt(sortedPosition, lastIndex + 1);
            quickSort(arr, pivot2, sortedPosition + 1, lastIndex);
        }
    }

    private static void quickSort2(int[] arr, int firstIndex, int lastIndex) {
        if (lastIndex > firstIndex) {
            int endmarker = partition2(arr, firstIndex, lastIndex);
            quickSort2(arr, firstIndex, endmarker - 1);
            quickSort2(arr, endmarker + 1, lastIndex);
        }
    }

    private static int partition2(int[] arr, int firstIndex, int lastIndex) {
        Random random = new Random();
        int pivotIndex = random.nextInt(firstIndex, lastIndex + 1);
        swapp(arr, pivotIndex, firstIndex);

        int i = firstIndex + 1;
        int j = lastIndex;
        int pivot = arr[firstIndex];

        while (i < j) {


            while (i <= lastIndex && arr[i] <= pivot) {
                i++;
            }

            while (j >= firstIndex && arr[j] > pivot) {
                j--;
            }

            if (j > i) {
                swapp(arr, i, j);
                i++;
                j--;
            }

        }

        swapp(arr, firstIndex, j);

        return j;

    }

    private static void swapp(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void quickSort3(int[] arr, int firstIndex, int lastIndex) {
        if (lastIndex > firstIndex) {
            int endmarker = partition3(arr, firstIndex, lastIndex);
            quickSort3(arr, firstIndex, endmarker);
            quickSort3(arr, endmarker + 1, lastIndex);
        }
    }

    private static int partition3(int[] arr, int firstIndex, int lastIndex) {
        int pivot = arr[firstIndex];

        int i = firstIndex + 1;
        int j = lastIndex;

        while (i < j) {
            do {

                i++;
            } while (arr[i] <= pivot);

            do {

                j--;
            } while (arr[j] > pivot);

            if (j > i) {
                swapp(arr, i, j);
            }
        }

        swapp(arr, firstIndex, j);
        return j;
    }


    public static void quickSort4(int[] arr, int low, int high) {
        if (low < high) {
            // choose pivot index (example: random pivot)
            int pivotIndex = low + (int) (Math.random() * (high - low + 1));
            int p = partition4(arr, low, high, pivotIndex);
            quickSort4(arr, low, p);       // Left side
            quickSort4(arr, p + 1, high); // Right side
        }
    }

    //working method
    private static int partition4(int[] arr, int low, int high, int pivotIndex) {
        swap(arr, low, pivotIndex);
        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;
        while (true) {
            do {
                i++;
            } while (arr[i] < pivot);
            do {
                j--;
            } while (arr[j] > pivot);
            if (i >= j) {
                return j;
            }
            swap(arr, i, j);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    private static int partitionTest(int low , int high ,int [] arr){

        Random random = new Random();

        int i = low +1;
        int j = high;
        int pivotIndex =  random.nextInt(low,high+1) ;
        int pivot = arr[pivotIndex];

        int temp = arr[low];
        arr[low] = arr[pivotIndex];
        arr[pivotIndex] = temp;

        while(i<j){

            while(i<=high ){
                if(arr[i] >= pivot){
                    break;
                }
                i++;
            }

            while(j>low ) {
                if (arr[j] < pivot) {
                    break;
                }
                j--;
            }

            if (j >i) {
                int temp3 = arr[j];
                arr[j] = arr[i];
                arr[i] = temp3;
            }

        }

        arr[low] = arr[j];
        arr[j] = pivot;
        return j;

    }

    private static void quickSortTest(int low , int high , int [] arr){

        if(high>low){
           int pivotIndex = partitionTest(low,high,arr);
              quickSortTest(low,pivotIndex-1,arr);
              quickSortTest(pivotIndex+1,high,arr);
        }
    }


}