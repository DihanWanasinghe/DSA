package Sorting;

public class MergeSort {
    public static void main(String[] args) {

        int [] arr = {5, 10, 4, 3, 1, 85, 32, 45, 69};
       arr=  mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array: ");
        for(int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
    }

    private static int [] mergeSort(int[] arr,int first, int last) {
        if(first <last){
            int mid = (first + last) / 2;
             int [] ar1 = mergeSort(arr,first,mid);
             int [] ar2 = mergeSort(arr,mid+1,last);
           int [] ar3 =    merge(ar1,ar2);
           return ar3;
        }
        return new int[] { arr[first] };
    }

    private static int[]  merge(int [] arr1, int [] arr2) {

        int j =0;
        int k = 0;
        int index = 0;
        int [] arr = new int [arr1.length + arr2.length];

        while(j <= arr1.length-1 && k <= arr2.length-1){


            if(arr1[j] <= arr2[k]){
                arr[index] = arr1[j];
                j++;
                index++;
            }else {
                arr[index] = arr2[k];
                k++;
                index++;
            }
        }


        while (j < arr1.length) {
            arr[index++] = arr1[j++];
        }
        while (k < arr2.length) {
            arr[index++] = arr2[k++];
        }

        return arr;
    }

    private  static void  mergeTest (int [] arr , int first , int last , int mid) {
         int [] arrayBase =  new int [last - first + 1];
         int currentIndex = 0;
            int i = first;
            int j = mid + 1;

            while (i <= mid && j <= last) {
                if(arr[j] < arr[i]) {
                    arrayBase[currentIndex] = arr[j];
                    j++;
                    currentIndex++;
                }else{
                    arrayBase[currentIndex] = arr[i];
                    i++;
                    currentIndex++;
                }

            }
            while(i <= mid) {
                arrayBase[currentIndex] = arr[i];
                i++;
                currentIndex++;
            }
            while(j <= last) {
                arrayBase[currentIndex] = arr[j];
                j++;
                currentIndex++;
            }

            for(int k = 0; k < arrayBase.length; k++) {
                arr[first + k] = arrayBase[k];
            }

    }

    private static void mergeSortTest(int [] arr, int first , int last){
        if(first < last){
            int mid = first + (last-first) /2 ;
            mergeSortTest(arr, first ,mid);
            mergeSortTest(arr, mid + 1, last);
            mergeTest(arr, first , last , mid);
        }
    }
}





