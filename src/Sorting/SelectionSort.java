package Sorting;

public class SelectionSort {
    public static void main(String [] args){

        int [] array = {5,10,4,3,1,85,32,45,69};

        selectionSort(array);

    }
    private static void selectionSort(int[] arr){


        int sortingPosition = arr.length-1;

        while(sortingPosition>0) {
            int maxIndex=sortingPosition;
            int maxValue = arr[maxIndex];
            for (int i = 0; i < sortingPosition; i++) {

                if (arr[i] > maxValue) {
                    maxValue = arr[i];
                    maxIndex = i;
                }

            }
            if(maxIndex != sortingPosition )
            {
                int temp = arr[sortingPosition];
                arr[sortingPosition]=maxValue;
                arr[maxIndex]=temp;

            }
            sortingPosition--;

        }
        System.out.println("Sorted array: ");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

}
