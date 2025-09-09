package Heap;

public class HeapRunner {
    public static void main(String[] args) {
        Heap heap = new Heap(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);
        heap.insert(4);
        heap.insert(7);
        heap.insert(2);
        heap.insert(6);
        heap.insert(5);
        heap.insert(12);

        heap.printHeap();
        Integer value = heap.minimumReplacement(6);
        System.out.println("Replaced min with 6, old min was: " + value);
        heap.printHeap();

//        System.out.println("Removed min: " + heap.removeMin()); // Should print 1
//        heap.printHeap();
//        System.out.println("Removed min: " + heap.removeMin());// Should print 3
//        heap.printHeap();
//        System.out.println("Removed min: " + heap.removeMin()); // Should print 4);
//        heap.printHeap();
//        System.out.println("Removed min: " + heap.removeMin()); // Should print 5
//        heap.printHeap();
//        System.out.println("Removed min: " + heap.removeMin()); // Should print 8
//        heap.printHeap();

    }
}
