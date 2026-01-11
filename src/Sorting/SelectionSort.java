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

    private static void selectionSortTest(int[] arr){
        //get the length , compare all  , get the higheset , move it to last
        //reduce the length by 1
         // compare all until i t, them move to the next end
         // until linght is 1

        int initialLength =  arr.length;
        while(initialLength >1){
            int maxIndex =0;
            for(int i=1;i<initialLength;i++){
                if(arr[i]>arr[maxIndex]){
                    maxIndex = i;
                }

            }
            if(maxIndex != initialLength-1){
                arr[initialLength-1] = arr[maxIndex] + arr[initialLength-1];
                arr[maxIndex] = arr[initialLength-1] - arr[maxIndex];
                arr[initialLength-1] = arr[initialLength-1] - arr[maxIndex];//swap
            }


            initialLength--;


        }

        System.out.println("Sorted array: ");
        for(int i=0;i<arr.length;i++) {
            System.out.print(arr[i] + " ");
        }




    }

}
