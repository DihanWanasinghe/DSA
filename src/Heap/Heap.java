package Heap;

import java.util.Arrays;

public class Heap {
    private Integer[] heapArray;
    private int heapSize;
    private int lastIndex;

    public Heap(int size) {
        this.heapArray = new Integer[size];
        this.heapSize = size;
        this.lastIndex = 0;
    }

    public void insert(int value) {

        if(lastIndex == heapArray.length) {
            System.out.println("Heap is full");
            return;
        }

        int currentIndex = lastIndex ;
        while(currentIndex > 0) {
            int parent  = (currentIndex -1) / 2;
            if(heapArray[currentIndex] > value) {
                heapArray[currentIndex] = heapArray[parent];
                currentIndex = parent;
            }else{
                break;
            }

        }
        heapArray[currentIndex] = value;
        lastIndex++;
    }


    public int removeMin(){
        if(lastIndex == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heapArray[0];

        lastIndex--;
        int currentIndex = 0;
        int lowestChildIndex = currentIndex*2 +1;
        while (  lastIndex > (currentIndex*2 +1) ){

            if((currentIndex*2+2 < lastIndex ) && (heapArray[lowestChildIndex] > heapArray[currentIndex*2+2]) ){
               lowestChildIndex = currentIndex*2 +2;
            }

            if( heapArray[lastIndex] >heapArray[lowestChildIndex] ){
                heapArray[currentIndex] = heapArray[lowestChildIndex];
                currentIndex = lowestChildIndex;
                lowestChildIndex = currentIndex*2 +1;
            }else {
                break ;
            }
        }

        heapArray[currentIndex] = heapArray[lastIndex];
        heapArray[lastIndex] = null;
        return min;
    }

    public Integer  minimumReplacement(int value) {
        if(lastIndex == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heapArray[0];
        int currentIndex = 0;
        int lowestChildIndex = currentIndex*2 +1;


        while (  lastIndex > lowestChildIndex ) {

            if(lowestChildIndex+1 < lastIndex && heapArray[lowestChildIndex] > heapArray[lowestChildIndex+1]) {
                lowestChildIndex = lowestChildIndex +1;
            }

            if(heapArray[lowestChildIndex] < value) {
                heapArray[currentIndex] = heapArray[lowestChildIndex];
                currentIndex = lowestChildIndex;
                lowestChildIndex = currentIndex*2 +1;
            } else {
                break;
            }
        }
        heapArray[currentIndex] = value;
        return min;
    }

    public void printHeap() {
        for (int i = 0; i < lastIndex; i++) {
            System.out.print(heapArray[i] + " ");
        }
        System.out.println();
        System.out.println("Last Index is " + lastIndex);
        System.out.println("Heap Size is " + heapSize);
    }
}

