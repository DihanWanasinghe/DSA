package Heap;

public class TestHeap {

    private int[] heapArray;
    private int heapSize;
    private int lastIndex;



    public TestHeap(int heapSize) {
        this.heapSize = heapSize;
        heapArray = new int[heapSize];
        lastIndex = 0;
    }

    public  TestHeap(int[] arr) {
        this.heapArray = arr;
        heapSize = arr.length;
        lastIndex = arr.length ;

        int lastParentIndex = (lastIndex - 2) / 2;

        for (int i = lastParentIndex; i >= 0; i--) {
            heapifyDown(i);
        }
//        arrangeHeap(lastParentIndex);

    }

    private void arrangeHeap(int parentIndex) {


        if(parentIndex <0 ){
            return;
        }
        heapifyDown(parentIndex);
        arrangeHeap(--parentIndex);

    }

    private void insert (int value) {
        if(lastIndex == heapSize) {
            System.out.println("Heap is Full");
            return;
        }
        heapArray[lastIndex] = value;
        heapifyUp(lastIndex);
        lastIndex++;

    }

    private int minmunExtraction(){
        int min = heapArray[0];
        heapArray[0] = heapArray[lastIndex-1];
        lastIndex--;
        heapifyDown(0);


        return  min;
    }

    private int minimumReplacement(int value) {
        if (lastIndex == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        int min = heapArray[0];
        heapArray[0] = value;
        heapifyDown(0);
        return min;
    }

    private int [] heapSort(){
        int [] sorted = new int[heapSize];
        int index = 0;
        while(lastIndex > 0){
           sorted[index] =   minmunExtraction();
           index++;
        }
        setHeap(sorted);
        return sorted;
    }

    private void setHeap(int [] arr){
        heapArray = arr;
        heapSize = arr.length;
        lastIndex = arr.length ;
    }

    private void heapifyUp(int currentIndex){

        if(currentIndex == 0 ){
            return;
        }
        int parentIndex = (currentIndex-1)/2;
        int temp = heapArray[currentIndex];
        if(temp <heapArray[parentIndex]){
            heapArray[currentIndex] = heapArray[parentIndex];
            heapArray[parentIndex] = temp;
                heapifyUp(parentIndex);
        }

        if(parentIndex >0 ){
           heapifyUp(parentIndex);
        }

    }

    private void heapifyDown(int currentIndex){
        if (2 * currentIndex + 1 >= lastIndex) return;
        int lowestChildIndex =2 * currentIndex + 1  ;
        if( (2*currentIndex+2) <lastIndex && heapArray[lowestChildIndex] > heapArray[lowestChildIndex+1] ) {

           lowestChildIndex = 2 * currentIndex + 2 ;
        }


        if(heapArray[lowestChildIndex] < heapArray[currentIndex]){
            int temp = heapArray[lowestChildIndex];
            heapArray[lowestChildIndex] = heapArray[currentIndex];
            heapArray[currentIndex] = temp;
            heapifyDown(lowestChildIndex);

        }
    }
}
