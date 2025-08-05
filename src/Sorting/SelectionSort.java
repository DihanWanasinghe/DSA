package Sorting;

public class SelectionSort {
    public static void main(String [] args){

        int [] array = {5,10,4,3,1,85,32,45,69};

        selectionSort(array);

    }
    private static void selectionSort(int[] arr){

        int maxIndex=0;
        int maxValue = arr[maxIndex];
        int sortingPosition = arr.length-1;
        boolean changed= false;
        while(sortingPosition>0) {
            for (int i = 0; i < sortingPosition; i++) {

                if (arr[i] > maxValue) {
                    maxValue = arr[i];
                    maxIndex = i;
                    changed = true;
                }

            }
            if(changed)
            {
                int temp = arr[sortingPosition];
                arr[sortingPosition]=maxValue;
                arr[maxIndex]=temp;
                changed = false;

            }
            sortingPosition--;
            maxIndex =sortingPosition;
            maxValue = arr[maxIndex];
        }
        System.out.println("Sorted array: ");
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
    }

}
