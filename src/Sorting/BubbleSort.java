package Sorting;

public class BubbleSort {
    public static void main(String[] args) {
        int [] arr = {5, 10, 4, 3, 1, 85, 32, 45, 69};
        bubbleSort(arr);
    }

    private static void bubbleSort(int [] arr) {
        int sortingIndex = arr.length - 1;

        while(sortingIndex !=0){
            boolean swapped = false;

            for(int i =0; i< sortingIndex; i++){
                if(arr[i] > arr[i+1]){
                    int temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                    swapped = true;
                }
            }
            if(!swapped){
                for(int j = arr.length-1; j >=0; j--){
                    System.out.print(arr[j] + " ");
                }
                return ;
            }
            sortingIndex--;
        }
    }
}
