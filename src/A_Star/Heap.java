package A_Star;

import A_Star.Vertex;
import A_Star.WrappedVertex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Heap {
    private ArrayList<WrappedVertex> heapArray;
    private int heapSize;
    private int lastIndex;
    private Map<Vertex, Integer> indexMap = new HashMap<>(); // Vertex -> index in heap


    public Heap(int heapSize) {
        this.heapSize = heapSize;
        heapArray = new ArrayList<WrappedVertex>();
        lastIndex = 0;
    }

    public Heap(){
        heapArray = new ArrayList<WrappedVertex>();
        lastIndex = 0;
    }

    public  Heap(ArrayList<WrappedVertex> arr) {
        this.heapArray = arr;
        heapSize = arr.size();
        lastIndex = arr.size() ;

        int lastParentIndex = (lastIndex - 2) / 2;

        for (int i = lastParentIndex; i >= 0; i--) {
            heapifyDown(i);
        }
//        arrangeHeap(lastParentIndex);

    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }

    private void arrangeHeap(int parentIndex) {


        if(parentIndex <0 ){
            return;
        }
        heapifyDown(parentIndex);
        arrangeHeap(--parentIndex);

    }

    public void insert (WrappedVertex value) {
        heapArray.add(value) ;
        indexMap.put(value.getVertex(), lastIndex);  // track index
        heapifyUp(lastIndex);
        lastIndex++;

    }

    public WrappedVertex minmunExtraction(){

        if (lastIndex == 0)
            throw new IllegalStateException("Heap empty");
        WrappedVertex min = heapArray.get(0);
        heapArray.set(0,heapArray.get(lastIndex-1));


        heapArray.remove(lastIndex-1); // remove last element
        lastIndex--;
        indexMap.remove(min.getVertex()); // remove from map
        if(lastIndex >0) {
            indexMap.put(heapArray.get(0).getVertex(), 0); // update index of new root
            heapifyDown(0);
        }



        return  min;
    }

    private WrappedVertex minimumReplacement(WrappedVertex value) {
        if (lastIndex == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        WrappedVertex min = heapArray.get(0);
        indexMap.remove(min.getVertex());
        heapArray.set(0,value);
        indexMap.put(value.getVertex(), 0);
        heapifyDown(0);
        return min;
    }

    private ArrayList<WrappedVertex> heapSort(){
        ArrayList<WrappedVertex> sorted = new ArrayList<WrappedVertex>();
        int index = 0;
        while(lastIndex > 0){
            sorted.add(minmunExtraction());
            index++;
        }
        setHeap(sorted);
        return sorted;
    }

    private void setHeap(ArrayList<WrappedVertex> arr){
        heapArray = arr;
        heapSize = arr.size();
        lastIndex = arr.size() ;
    }

    private void heapifyUp(int currentIndex){

        if(currentIndex == 0 ){
            return;
        }
        int parentIndex = (currentIndex-1)/2;
        WrappedVertex temp = heapArray.get(currentIndex);
        if(temp.getTotalCost() <heapArray.get(parentIndex).getTotalCost()){
            heapArray.set(currentIndex,heapArray.get(parentIndex));
            heapArray.set(parentIndex,temp);

            indexMap.put(heapArray.get(currentIndex).getVertex(), currentIndex);
            indexMap.put(heapArray.get(parentIndex).getVertex(), parentIndex);
            heapifyUp(parentIndex);
        }
    }

    private void heapifyDown(int currentIndex){
        if (2 * currentIndex + 1 >= lastIndex) return;
        int lowestChildIndex =2 * currentIndex + 1  ;
        if( (2*currentIndex+2) <lastIndex && heapArray.get(lowestChildIndex).getTotalCost() > heapArray.get(lowestChildIndex+1).getTotalCost() ) {

            lowestChildIndex = 2 * currentIndex + 2 ;
        }


        if(heapArray.get(lowestChildIndex).getTotalCost() < heapArray.get(currentIndex).getTotalCost()){
            WrappedVertex temp = heapArray.get(lowestChildIndex);
            heapArray.set(lowestChildIndex,heapArray.get(currentIndex));
            heapArray.set(currentIndex,temp);

            indexMap.put(heapArray.get(currentIndex).getVertex(), currentIndex);
            indexMap.put(heapArray.get(lowestChildIndex).getVertex(), lowestChildIndex);

            heapifyDown(lowestChildIndex);

        }
    }

    public void decreaseKey(Vertex v, int newDistance) {
        Integer index = indexMap.get(v);
        if(index == null) return; // not in heap
        heapArray.get(index).setTotalCost(newDistance);
        heapifyUp(index);
    }

}
